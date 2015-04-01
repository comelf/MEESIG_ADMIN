package com.meesig.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
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
import com.meesig.model.ShippingDayManage;
import com.meesig.model.ShippingDays;
import com.meesig.model.ShippingPrice;
import com.meesig.model.ShippingPriceManage;
import com.meesig.model.Shop;

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

	public int getTotalUser() {
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
		int state  = item.getItem_option_state();
		ItemOptionManage iom = item.getIom();
		iom.setItems_item_id(item.getItem_id());
		switch (state) {
		
		//옵션없음
		case 1:
			break;
			
		//옵션상품
		case 2:
			itemMapper.insertItemOptions(iom);
			break;
			
		default:
			break;
		}
		
		
	}

	private void insertItemIntoShippingPriceTable(AddItem item)  throws Exception{
		int state = item.getItem_shipping_price_state();
		ShippingPrice shippingPrice = new ShippingPrice();
		shippingPrice.setItems_item_id(item.getItem_id());
		ShippingPriceManage spm = item.getSpm();
		
		switch (state) {

		//배송비 무료
		case 1:
			break;
			
		//단일 가격
		case 2:
			shippingPrice.addPrice(1, spm.getPriceOpt2(), "");
			itemMapper.insertItemShippingPriceWithShippingPrice(shippingPrice);
			break;
		
		//선택적 (옵션)
		case 3:
			shippingPrice.addPriceList(spm.getPriceOpt3Pri(),spm.getPriceOpt3Des());
			itemMapper.insertItemShippingPriceWithShippingPrice(shippingPrice);
			break;
			
		default:
			break;
		}
		
	}

	private void insertItemIntoShippingDayTable(AddItem item) throws Exception{
		int state = item.getItem_shipping_day_state();
		ShippingDays shippingDays = new ShippingDays();
		shippingDays.setItem_id(item.getItem_id());
		ShippingDayManage sdm = item.getSdm();
		
		switch (state) {
		//당일배송(1)
		case 1:
			shippingDays.setDay_send_time_string(sdm.getDayOpt1time());
			shippingDays.setDays(sdm.getDayOpt1());
			itemMapper.insertItemShippingDayWithDays(shippingDays);
			break;
			
		//익일배송(2)
		case 2:
			shippingDays.setDay_send_time_string(sdm.getDayOpt2time());
			shippingDays.setDays(sdm.getDayOpt2());
			itemMapper.insertItemShippingDayWithDays(shippingDays);
			break;
		
		//배송일 지정(3)
		case 3:
			shippingDays.setDay_send_day(Date.valueOf(sdm.getDayOpt3()));
			itemMapper.insertItemIntoShippingDay(item);
			break;

		default:
			break;
		}
		
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
			type= "i.item_name";
			break;
		case "shop":
			type = "s.shop_name";
			break;
		case "location":
			type = "l.location_name";
			break;
		case "category":
			type = "c.category_name";
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

}
