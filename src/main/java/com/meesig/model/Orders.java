package com.meesig.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

import org.apache.ibatis.type.Alias;

import com.meesig.support.state.OrderState;
import com.meesig.util.ConvertUtil;

@Alias("orders")
@Data
public class Orders {
	private long order_id;
	private int users_user_id;
	private String order_name;
	private Date order_date;
	private String order_call_num;
	private String order_payment;
	private Date order_payment_date;
	private int order_state;
	private String order_rid;
	private long order_pay_oid;
	
	private String order_timestamp;

	private int order_shipping_price;
	private long order_sum_price;
	private long order_total_price;
	
	private int order_use_coupon_price;
	private int order_use_point_price;
	private long order_payment_price;
	
	private int order_use_coupon;
	private int order_use_point; 
	
	private List<OrderBundle> bundles;
	private OrderDelivery delivery;
	private OrderPayment payment;
	
	private int[] bundleState;
	public Orders(){
		
	}
	
	public Orders(long totalPrice, int userId, String orderName) {
		this.users_user_id = userId;
		this.order_total_price = totalPrice;
		this.order_state = OrderState.ON_HOLD;
		this.order_name = orderName;
		
		Date now = new Date();
		this.order_timestamp = ConvertUtil.dateForTimeStamp(now);
		this.order_date = now;
		
		this.order_use_coupon_price = 0;
		this.order_use_point_price = 0;
		updatePaymentPrice();
	}

	public String getConvertedTotalPrice(){
		return ConvertUtil.currency(order_total_price);
	}
	
	public String getConvertedShippingPrice(){
		return ConvertUtil.currency(order_shipping_price);
	}
	
	public String getConvertedMultiplyPrice(){
		return ConvertUtil.currency(order_sum_price);
	}
	
	public String getConvertOrderState(){
		return OrderState.getStateString(order_state);
	}
	
	public String getConvertedPaymentPrice(){
		return ConvertUtil.currency(order_payment_price);
	}

	public String getConvertOrderDate(){
		return ConvertUtil.dateForMyPage(order_date);
	}
	
	public String getConvertOrderDateResult(){
		return ConvertUtil.dateForResultPage(order_date);
	}
	
	public String getConvertedPointPrice(){
		return ConvertUtil.currency(order_use_point_price);
	}
	
	public String getConvertedCouponPrice(){
		return ConvertUtil.currency(order_use_coupon_price);
	}
	
	public String getConvertedDiscountPrice(){
		return ConvertUtil.currency(order_use_coupon_price + order_use_point_price);
	}

	public void useCoupon(int coupon_price) {
		order_use_coupon_price = coupon_price;
		updatePaymentPrice();
	}
	
	public void usePoint(int point) {
		order_use_point_price = point;
		updatePaymentPrice();
	}

	public void updatePaymentPrice() {
		order_payment_price = order_total_price - order_use_coupon_price - order_use_point_price;
	}
	
	public OrderDelivery getDelivery(){
		if(this.delivery == null){
			return new OrderDelivery();
		}else{
			return this.delivery;
		}
	}

	public void checkBundle() {
		bundleState = new int[4];
		for(OrderBundle bundle : bundles){
			int state = bundle.getBundle_state();
			switch (state) {
			case OrderState.PROCESSING:
				bundleState[0]++;
				break;
			case OrderState.SHOP_ORDER:
				bundleState[1]++;
				break;
			case OrderState.SHIPPED:
			case OrderState.RIDE:
				bundleState[2]++;
				break;
			case OrderState.COMPLETED:
				bundleState[3]++;
				break;
			default:
				break;
			}
			
		}
	}
	
}
