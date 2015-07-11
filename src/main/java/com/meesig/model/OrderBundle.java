package com.meesig.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

import org.apache.ibatis.type.Alias;

import com.meesig.support.state.BundleState;
import com.meesig.util.ConvertUtil;

@Alias("orderBundle")
@Data
public class OrderBundle {
	private long bundle_id;
	private long orders_order_id;
	private int shops_shop_id;
	private int bundle_state;
	
	private Date bundle_reserve_date;
	
	private Date bundle_shop_order;
	private Date bundle_packing_date;
	private Date bundle_send_date;
	private Date bundle_ride_date;
	private Date bundle_recive_date;
	
	
	private BundleDelivery shipping;
	private OrderDelivery delivery;
	private List<OrderMenu> orderMenus;
	
	public OrderBundle(){
		
	}
	

	public String getConvertedChargeShippingPrice(){
		return ConvertUtil.dateForMenu(bundle_reserve_date);
	}
	
	public String getConvertedBundleState(){
		return BundleState.convert(bundle_state);
	}
	
}
