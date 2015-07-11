package com.meesig.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.meesig.model.BundleDelivery;
import com.meesig.model.MenuOrderInfo;
import com.meesig.model.OrderBundle;
import com.meesig.model.OrderDelivery;
import com.meesig.model.OrderForList;
import com.meesig.model.OrderMenu;
import com.meesig.model.Orders;
import com.meesig.model.ShopOrders;
import com.meesig.model.SumOfDays;
import com.meesig.support.state.BundleState;

public interface OrderMapper {
	@Select("select o.order_id, o.order_date, o.order_name, o.order_payment_price, o.order_state, u.user_login_id, u.user_name, u.user_phone "
			+ "from orders as o "
			+ "left join users as u on o.users_user_id = u.user_id "
			+ "order by o.order_id desc limit #{start}, #{count}")
	List<OrderForList> selectSortedOrderListForManagement(@Param("start")int start, @Param("count")int count);

	@Select("select count(*) from orders")
	int countTotalOrder();

	@Select("select o.order_id, o.order_date, o.order_name, o.order_payment_price, o.order_state, u.user_login_id, u.user_name "
			+ "from orders as o "
			+ "left join users as u on o.users_user_id = u.user_id "
			+ "where ${field} like '%${query}%' order by o.order_id desc limit 100")
	List<OrderForList> searchOrderList(@Param("field")String field, @Param("query")String query);

	@Select("select * from orders where order_id = #{orderId}")
	@Results(value ={
			@Result(property="order_id", column="order_id"),
			@Result(property="bundles", column="order_id", javaType=List.class, many=@Many(select=("selectOrderBundleByOrderId"))),
			@Result(property="delivery", column="order_id", javaType=OrderDelivery.class, one=@One(select=("selectOrderDeliveryByOrderId")))
	})
	Orders selectOrderByOrderId(@Param("orderId")long order_id);
	
	@Select("select * from order_bundle "
			+ "where orders_order_id = #{order_id} order by shops_shop_id, bundle_reserve_date")
	@Results(value ={
			@Result(property="orderMenus", column="bundle_id", javaType=List.class, many=@Many(select=("selectOrderMenuByOrderBundleId"))),
			@Result(property="shipping", column="bundle_id", javaType=BundleDelivery.class, one=@One(select=("selectBundleDeliveryByBundelId")))
	})
	List<OrderBundle> selectOrderBundleByOrderId(@Param("order_id")long order_id);

	@Select("select * from order_bundle "
			+ "where bundle_reserve_date = #{date} and shops_shop_id = #{shopId}")
	@Results(value ={
			@Result(property="orders_order_id", column="orders_order_id"),
			@Result(property="bundle_id", column="bundle_id"),
			@Result(property="orderMenus", column="bundle_id", javaType=List.class, many=@Many(select=("selectOrderMenuByOrderBundleId"))),
			@Result(property="shipping", column="orders_order_id", javaType=BundleDelivery.class, one=@One(select=("selectOrderDeliveryByOrderId")))
	})
	List<OrderBundle> selectOrderBundleByShopIdAndDate(@Param("shopId")int shopId,@Param("date")Date date);
	
	@Select("select * from order_bundle "
			+ "where bundle_reserve_date = #{date} and shops_shop_id = #{shopId} and bundle_state =#{state}")
	@Results(value ={
			@Result(property="orders_order_id", column="orders_order_id"),
			@Result(property="bundle_id", column="bundle_id"),
			@Result(property="orderMenus", column="bundle_id", javaType=List.class, many=@Many(select=("selectOrderMenuByOrderBundleId"))),
			@Result(property="shipping", column="orders_order_id", javaType=BundleDelivery.class, one=@One(select=("selectOrderDeliveryByOrderId")))
	})
	List<OrderBundle> selectOrderBundleByShopIdAndDateAndState(@Param("shopId")int shopId,@Param("date")Date date, @Param("state")int state);
	
	@Select("select * from order_delivery where orders_order_id = #{orderId}")
	OrderDelivery selectOrderDeliveryByOrderId(@Param("orderId")long orderId);
	
	@Select("select * from order_menu as o "
			+ "join ITEMS as i on o.items_item_id = i.item_id "
			+ "join media as m on i.media_media_id = m.media_id "
			+ "where o.order_bundle_id = #{bundle_id} order by o.om_isBundle")
	List<OrderMenu> selectOrderMenuByOrderBundleId(@Param("bundle_id")long bundle_id);

	@Select("select * from bundle_delivery where ORDER_bundle_id = #{bundle_id}")
	BundleDelivery selectBundleDeliveryByBundelId(@Param("bundle_id")long bundle_id);

	@Select("select s.shop_id,i.item_id, s.shop_name, i.item_name, sum(if(b.bundle_state=4, m.om_item_count,0)) as sum_state_process, sum(if(b.bundle_state=5, m.om_item_count,0)) as sum_state_shop,sum(if(b.bundle_state=5 OR b.bundle_state=6, m.om_item_count,0)) as sum_state_delivery "
			+ "from items as i "
			+ "join shops as s on i.shops_shop_id = s.shop_id "
			+ "left join order_menu as m on i.item_id = m.items_item_id "
			+ "left join ORDER_BUNDLE as b on m.order_bundle_id = b.bundle_id "
			+ "group by i.item_id order by s.shop_id")
	List<MenuOrderInfo> selectAllMenuInfoByState();

	@Select("select shop_id, shop_name, shop_state from shops")
	@Results(value ={
			@Result(property="shop_id", column="shop_id"),
			@Result(property="sumOfDays", column="shop_id", javaType=List.class, many=@Many(select=("selectSumItemsByDaysForOutput")))
	})
	List<ShopOrders> selectAllShopWithItemsForOutput();
	
	@Select("select shop_id, shop_name, shop_state from shops")
	@Results(value ={
			@Result(property="shop_id", column="shop_id"),
			@Result(property="sumOfDays", column="shop_id", javaType=List.class, many=@Many(select=("selectSumItemsByDaysForDelivery")))
	})
	List<ShopOrders> selectAllShopWithItemsForDelivery();
	
	@Select("select sum(m.om_item_count) as sum, b.bundle_reserve_date as reserve_date  from order_menu as m "
			+ "join ORDER_BUNDLE as b on m.order_bundle_id = b.bundle_id "
			+ "where b.bundle_state = "+BundleState.SHOP_ORDER+" and b.shops_shop_id = #{shop_id} group by b.bundle_reserve_date")
	List<SumOfDays> selectSumItemsByDaysForDelivery(@Param("shop_id")int shop_id);
	
	@Select("select sum(m.om_item_count) as sum, b.bundle_reserve_date as reserve_date  from order_menu as m "
			+ "join ORDER_BUNDLE as b on m.order_bundle_id = b.bundle_id "
			+ "where b.bundle_state = "+BundleState.PROCESSING+" and b.shops_shop_id = #{shop_id} group by b.bundle_reserve_date")
	List<SumOfDays> selectSumItemsByDaysForOutput(@Param("shop_id")int shop_id);

	@Update("update ORDER_BUNDLE "
			+ "join orders on ORDER_BUNDLE.ORDERS_ORDER_ID = orders.ORDER_ID "
			+ "set bundle_state = #{state}, order_state = #{state}, bundle_shop_order = current_date() where bundle_reserve_date = #{date} and shops_shop_id = #{shopId}")
	void updateOrderState(@Param("shopId")int shopId,@Param("date")Date date, @Param("state")int state);

	@Insert("insert into bundle_delivery (ORDER_bundle_id, delivery_state, delivery_name, delivery_code, delivery_link) values(#{ORDER_bundle_id}, #{delivery_state}, #{delivery_name}, #{delivery_code}, #{delivery_link})")
	void insertBundleDelivery(BundleDelivery bd);

	@Update("update ORDER_BUNDLE "
			+ "set bundle_state = "+BundleState.SHIPPED+", bundle_send_date = current_date() where bundle_id = #{bundleId}")
	void updateBundelStateDelivery(@Param("bundleId")long bundleId);

	@Select("select * from order_bundle "
			+ "where orders_order_id = (select orders_order_id from order_bundle where bundle_id = #{bundleId});")
	List<OrderBundle> selectOrderBundleListByBundleId(@Param("bundleId")long bundleId);
	
	@Update("update orders set order_state = #{state} where order_id = (select orders_order_id from order_bundle where bundle_id = #{bundleId})")
	void updateOrderStateByBundleId(@Param("bundleId")long bundleId, @Param("state")int state);

	@Update("update bundle_delivery set delivery_state=#{delivery_state}, delivery_name=#{delivery_name}, delivery_code=#{delivery_code}, delivery_link=#{delivery_link} where delivery_id = #{delivery_id}")
	void updateBundleDelevery(BundleDelivery bd);

	
}
