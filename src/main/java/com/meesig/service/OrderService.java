package com.meesig.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.meesig.mapper.OrderMapper;
import com.meesig.model.BundleDelivery;
import com.meesig.model.MenuOrderInfo;
import com.meesig.model.OrderBundle;
import com.meesig.model.OrderForList;
import com.meesig.model.Orders;
import com.meesig.model.ShopOrders;
import com.meesig.support.state.BundleState;
import com.meesig.support.state.OrderState;

@Service
public class OrderService {
	
	@Autowired
	OrderMapper orderMapper;

	@Autowired
	DataSourceTransactionManager transactionManager;
	
	private int total = 0;
	
	public List<OrderForList> selectSortedOrderList(String sort, int count,
			int page) {
		total = countTotalOrder();

		return orderMapper.selectSortedOrderListForManagement((page - 1) * count, count);
	}
	
	private int countTotalOrder() {
		return orderMapper.countTotalOrder();
	}
	
	public int getTotalOrder(){
		return total;
	}

	public List<OrderForList> searchOrderList(String type, String query) {
		String field = "";
		switch (type) {
		case "name":
			field= "o.order_name";
			break;
		case "oid":
			field = "o.order_id";
			break;
		case "userId":
			field = "u.user_login_id";
			break;
		case "userName":
			field = "u.user_name";
			break;
		case "userPhone":
			field = "u.user_phome";
			break;
		default:
			return null;
		}

		return orderMapper.searchOrderList(field, query);
	}

	public Orders selectOrderByOrderId(long order_id) {
		return orderMapper.selectOrderByOrderId(order_id);
	}

	public List<MenuOrderInfo> selectOrderListByShops() {
		return orderMapper.selectAllMenuInfoByState();
	}

	public List<ShopOrders> selectAllShopItemsForOutput() {
		return orderMapper.selectAllShopWithItemsForOutput();
	}
	
	public List<ShopOrders> selectAllShopItemsForDelivery() {
		return orderMapper.selectAllShopWithItemsForDelivery();
	}

	public List<Date> getOutDays() {
		List<Date> days = new ArrayList<Date>();
		Date now = new Date();
		for (int i = 0; i < Calendar.DAY_OF_WEEK; i++) {
			Calendar c = Calendar.getInstance();
			c.setTime(now);
			c.add(Calendar.DATE, i);
			Date tDate = c.getTime();
			days.add(tDate);
		}
		return days;
	}

	public void updateOrderStateProcess(int shopId, Date date) {
		orderMapper.updateOrderState(shopId, date, OrderState.SHOP_ORDER);
	}

	public List<OrderBundle> selectOrderBundleWithDelivery(int shopId,
			Date date) {
		return orderMapper.selectOrderBundleByShopIdAndDate(shopId, date);
	}

	public void updateBundelStateAndDelivery(List<BundleDelivery> deliveryList) {

		DefaultTransactionDefinition dtd = new DefaultTransactionDefinition();
		dtd.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(dtd);

		try{
			for(BundleDelivery bd : deliveryList){
				BundleDelivery obd = orderMapper.selectBundleDeliveryByBundelId(bd.getORDER_bundle_id());
				if(obd==null || obd.getDelivery_state() != bd.getDelivery_state()){
					orderMapper.insertBundleDelivery(bd);
				}else{
					bd.setDelivery_id(obd.getDelivery_id());
					orderMapper.updateBundleDelevery(bd);
				}
				
				orderMapper.updateBundelStateDelivery(bd.getORDER_bundle_id());
				List<OrderBundle> bundles = orderMapper.selectOrderBundleListByBundleId(bd.getORDER_bundle_id());
				
				int stateMin = BundleState.SHIPPED;
				for(OrderBundle bundle : bundles){
					int bMin = bundle.getBundle_state();
					stateMin = Math.min(stateMin, bMin);
				}
				orderMapper.updateOrderStateByBundleId(bd.getORDER_bundle_id(), stateMin);
			}
		} catch (Exception e) {
			transactionManager.rollback(status);
		}
		
		transactionManager.commit(status);
	}

	public List<OrderBundle> selectOrderBundleWithDeliveryForShipping(
			int shopId, Date date) {
		return orderMapper.selectOrderBundleByShopIdAndDateAndState(shopId, date, BundleState.SHOP_ORDER);
	}
	
}
