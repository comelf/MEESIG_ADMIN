package com.meesig.service;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.meesig.mapper.ItemMapper;
import com.meesig.model.AddItem;
import com.meesig.model.Category;
import com.meesig.model.Item;
import com.meesig.model.ItemForList;
import com.meesig.model.ItemOptionManage;
import com.meesig.model.OptionBlock;
import com.meesig.model.PriceOption;
import com.meesig.model.ShippingDayManage;
import com.meesig.model.ShippingDays;
import com.meesig.model.ShippingPrice;
import com.meesig.model.ShippingPriceManage;
import com.meesig.model.Shop;
import com.meesig.support.state.ShippingDayState;
import com.meesig.support.state.ShippingPriceState;

@Repository
public class ItemDBManager {
	private static final Logger LOG = LoggerFactory.getLogger(ItemDBManager.class);
	private final int SQL_SUCSSES = 1;

	@Autowired
	ItemMapper itemMapper;

	@Autowired
	DataSourceTransactionManager transactionManager;

	private int total = 0;

	public List<ItemForList> selectSortedItemList(String sort, int count,
			int page) {
		total = countTotalItem();

		return itemMapper.selectSortedItemListForManagement((page - 1) * count, count);
	}

	private int countTotalItem() {
		return itemMapper.countTotalItem();
	}

	public int getTotalItem() {
		return this.total;
	}

	/*
	 * Transaction 적용 향후 AOP로 변환 필요
	 */
	public boolean createItmeInAdminPage(AddItem item) {
		if (item.getMedia_media_id() < 1) {
			return false;
		}
		
		

		DefaultTransactionDefinition dtd = new DefaultTransactionDefinition();
		dtd.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(dtd);

		try {
			//Items table
			itemMapper.createItemInAdminPage(item);
			
			insertItemIntoShippingDayTable(item);
			insertItemIntoShippingPriceTable(item);
			insertItemIntoItemOptionTable(item);
			
		} catch (Exception e) {
			LOG.warn("Item insert error : {}", e.getMessage());
			e.printStackTrace();
			transactionManager.rollback(status);
			return false;
		}

		transactionManager.commit(status);
		return true;
	}

	private void insertItemIntoItemOptionTable(AddItem item) throws Exception{
		ItemOptionManage iom = item.getIom();
		
		if(iom==null){
			return;
		}
		
		iom.setItems_item_id(item.getItem_id());
		if(item.isItem_has_option()){
			itemMapper.insertItemOptions(iom);
		}
		
	}
	private void selectItemIntoItemOptionTable(AddItem item) {
		ItemOptionManage iom  = new ItemOptionManage();
		iom.setItems_item_id(item.getItem_id());
		List<OptionBlock> optionBlocks = itemMapper.selectItemOptionByItemId(item.getItem_id());
		for(OptionBlock optionBlock : optionBlocks){
			optionBlock.setOptionRow(itemMapper.selectItemOptionRowByItemId(item.getItem_id(), optionBlock.getOption_id()));
		}		
		
		iom.setOptionBlocks(optionBlocks);
		item.setIom(iom);
		
	}
	private void insertItemIntoShippingPriceTable(AddItem item)  throws Exception{
		int state = item.getItem_shipping_price_state();
		ShippingPrice shippingPrice = new ShippingPrice();
		shippingPrice.setItems_item_id(item.getItem_id());
		ShippingPriceManage spm = item.getSpm();
		
		switch (state) {

		//배송비 무료
		case ShippingPriceState.FREE:
			break;
			
		//단일 가격
		case ShippingPriceState.STATIC_PRICE:
			shippingPrice.addPrice(1, spm.getPriceOpt2(), "");
			itemMapper.insertItemShippingPriceWithShippingPrice(shippingPrice);
			break;
		
		//선택적 (옵션)
		case ShippingPriceState.DYNAMIC_PRICE:
			shippingPrice.addPriceList(spm.getPriceOpt3Pri(),spm.getPriceOpt3Des());
			itemMapper.insertItemShippingPriceWithShippingPrice(shippingPrice);
			break;
			
		default:
			break;
		}
		
	}

	private void selectItemIntoShippingPriceTable(AddItem item) {
		ShippingPrice shippingPrice = new ShippingPrice();
		List<PriceOption> price_options = itemMapper.selectShippingPriceyOptionByItemId(item.getItem_id());
		shippingPrice.setItems_item_id(item.getItem_id());
		shippingPrice.setPrice_options(price_options);
		ShippingPriceManage spm = new ShippingPriceManage();
		switch (item.getItem_shipping_price_state()) {

		//배송비 무료
		case ShippingPriceState.FREE:
			break;
			
		//단일 가격
		case ShippingPriceState.STATIC_PRICE:
			spm.setPriceOpt2(shippingPrice.getPrice_options().get(0).getPrice());
			break;
		
		//선택적 (옵션)
		case ShippingPriceState.DYNAMIC_PRICE:
			String[] priceOpt3Pri = new String[price_options.size()];
			String[] priceOpt3Des = new String[price_options.size()];
			
			for(int i =0; i<price_options.size(); i++){
				priceOpt3Pri[i] = String.valueOf(price_options.get(i).getPrice());
				priceOpt3Des[i] = price_options.get(i).getDesc();
			}
			
			spm.setPriceOpt3Pri(priceOpt3Pri);
			spm.setPriceOpt3Des(priceOpt3Des);
			break;
			
		default:
			break;
		}
		item.setSpm(spm);
	}
	
	private void insertItemIntoShippingDayTable(AddItem item) throws Exception{
		int state = item.getItem_shipping_day_state();
		ShippingDays shippingDays = new ShippingDays();
		shippingDays.setItem_id(item.getItem_id());
		ShippingDayManage sdm = item.getSdm();
		
		switch (state) {
		//당일배송(1)
		case ShippingDayState.ONE_DAY_DELIVERY:
			shippingDays.setDay_send_time_string(sdm.getDayOpt1time());
			shippingDays.setDays(sdm.getDayOpt1());
			itemMapper.insertItemShippingDayWithDays(shippingDays);
			break;
			
		//익일배송(2)
		case ShippingDayState.TWO_DAY_DELIVERY:
			shippingDays.setDay_send_time_string(sdm.getDayOpt2time());
			shippingDays.setDays(sdm.getDayOpt2());
			itemMapper.insertItemShippingDayWithDays(shippingDays);
			break;
		
		//배송일 지정(3)
		case ShippingDayState.SPECIFY_DAY_DELIVERY:
			shippingDays.setDay_send_day(Date.valueOf(sdm.getDayOpt3()));
			itemMapper.insertItemIntoShippingDay(item);
			break;

		default:
			break;
		}
		
	}

	private void selectItemIntoShippingDayTable(AddItem item) {
		ShippingDays shippingDays = itemMapper.selectShippingDayByItemId(item.getItem_id());
		ShippingDayManage sdm = new ShippingDayManage();
		switch (item.getItem_shipping_day_state()) {
		case ShippingDayState.ONE_DAY_DELIVERY:
			sdm.setDayOpt1(shippingDays.getDays());
			sdm.setDayOpt1time(shippingDays.getDay_send_time().toString());
			break;
		case ShippingDayState.TWO_DAY_DELIVERY:
			sdm.setDayOpt2(shippingDays.getDays());
			sdm.setDayOpt2time(shippingDays.getDay_send_time().toString());
			break;
		case ShippingDayState.SPECIFY_DAY_DELIVERY:
			sdm.setDayOpt3(shippingDays.getDay_send_day().toString());
			break;
		default:
			break;
		}
		item.setSdm(sdm);
	}
	
	public List<Category> selectCategoryList() {
		return itemMapper.selectCatetoryListAll();
	}

	public List<Shop> selecShopList() {
		return itemMapper.selectShopListAll();
	}

	public boolean updateItemInfo(Item item) {
		if (item.getMedia_media_id() < 1) {
			item.setMedia_media_id(1);
		}

		if (itemMapper.updateItemInfoById(item) == SQL_SUCSSES) {
			return true;
		}
		return false;
	}

	public void insertCategory(Category category) {
		itemMapper.insertCategory(category);
	}

	public Category selectCategory(int category_id) {
		return itemMapper.selectCategoryById(category_id);
	}

	public void updateCategory(Category category) {
		itemMapper.updateCategoryById(category);
	}

	public List<ItemForList> searchItmeList(String type, String query) {
		String field = "";
		switch (type) {
		case "name":
			field= "i.item_name";
			break;
		case "shop":
			field = "s.shop_name";
			break;
		case "location":
			field = "l.location_name";
			break;
		case "category":
			field = "c.category_name";
			break;
		default:
			return null;
		}

		return itemMapper.searchItemList(field, query);
	}

	public boolean isExistItemId(Item item) {
		String path_url = item.getItem_path_url();

		if (path_url == null) {
			return true;
		}

		if (path_url.isEmpty()) {
			return true;
		}

		Item itemInDB = itemMapper.findItemIdByPathUrl(path_url);

		if (itemInDB != null) {
			return true;
		} else {
			return false;
		}
	}

	public void deleteCategory(int category_id) {
		itemMapper.deleteCategoryById(category_id);
	}

	public int countItems() {
		return itemMapper.countItems();
	}

	public String isValidItemStates(AddItem addItem) {
		String sds = addItem.validationOfShippingDayState();
		if(sds!= null){
			return sds;
		}
		
		String sps = addItem.validationOfShippingPriceState();
		if(sps!= null){
			return sps;
		}
		
		String ios = addItem.validationOfItemOptionState();
		if(ios!= null){
			return ios;
		}
		
		
		return null;
	}

	public AddItem selectItemById(int item_id) {
		AddItem item = itemMapper.selectItemById(item_id);
		if(item==null){
			return null;
		}
		selectItemIntoShippingDayTable(item);
    	selectItemIntoShippingPriceTable(item);
    	selectItemIntoItemOptionTable(item);
		return item;
	}

	public boolean updateItmeInAdminPage(AddItem item) {
		if (item.getMedia_media_id() < 1) {
			return false;
		}

		DefaultTransactionDefinition dtd = new DefaultTransactionDefinition();
		dtd.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(dtd);

		try {
			//Items table
			itemMapper.updateItemInAdminPage(item);
			
			itemMapper.deleteItemShippingDay(item.getItem_id());
			insertItemIntoShippingDayTable(item);
			itemMapper.deleteItemShippingPrice(item.getItem_id());
			insertItemIntoShippingPriceTable(item);
			itemMapper.deleteItemOption(item.getItem_id());
			insertItemIntoItemOptionTable(item);
			
		} catch (Exception e) {
			LOG.warn("Item insert error : {}", e.getMessage());
			e.printStackTrace();
			transactionManager.rollback(status);
			return false;
		}

		transactionManager.commit(status);
		return true;
	}


}
