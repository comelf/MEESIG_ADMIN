package com.meesig.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.meesig.model.AddItem;
import com.meesig.model.Category;
import com.meesig.model.Item;
import com.meesig.model.ItemForList;
import com.meesig.model.ItemOptionManage;
import com.meesig.model.ShippingDays;
import com.meesig.model.ShippingPrice;
import com.meesig.model.Shop;

public interface ItemMapper {

	@Select("select i.item_id, c.category_name, s.shop_name, i.item_name, i.item_sell_price, i.item_option_state, l.location_name, m.media_file_name, m.media_file_extension, m.media_file_path "
			+ "from items as i "
			+ "left join item_category as c on i.item_category_id = c.category_id "
			+ "left join shops as s on i.shops_shop_id  = s.shop_id "
			+ "join locations as l on s.LOCATIONS_LOCATION_ID =l.location_id "
			+ "join media as m on i.media_media_id = m.media_id "
			+ "order by i.item_id desc limit #{start}, #{count}")
	List<ItemForList> selectSortedItemListForManagement(@Param("start")int start, @Param("count")int count);

	@Select("select count(item_id) from items")
	int countTotalItem();

	@Select("select * from shops")
	List<Shop> selectShopListAll();

	@Insert("insert into items (item_category_id, shops_shop_id, item_name, item_path_url, media_media_id, item_description, item_sell_price, item_supply_price, item_content, item_origin, item_shipping, item_sale_price, item_daily_stock, item_option_state) "
			+ "values (#{item_category_id}, #{shops_shop_id}, #{item_name}, #{item_path_url}, #{media_media_id}, #{item_description}, #{item_sell_price}, #{item_supply_price}, #{item_content}, #{item_origin}, #{item_shipping}, #{item_sale_price}, #{item_daily_stock}, #{item_option_state} )")
	@SelectKey(keyProperty = "item_id", before = false, resultType = int.class, statement = { "SELECT @@IDENTITY" })
	int createItemInAdminPage(AddItem item);

	@Update("update items set item_category_id = #{item_category_id}, shops_shop_id = #{shops_shop_id}, item_name = #{item_name}, item_path_url = #{item_path_url}, "
			+ "media_media_id = #{media_media_id}, item_description = #{item_description}, item_sell_price = #{item_sell_price}, item_supply_price = #{item_supply_price}, "
			+ "item_content = #{item_content}, item_origin = #{item_origin}, item_shipping = #{item_shipping}, item_sale_price = #{item_sale_price}, item_daily_stock = #{item_daily_stock}, item_option_state = #{item_option_state} "
			+ "where item_id = #{item_id}")
	int updateItemInfoById(Item item);

	@Select("select i.item_id, c.category_name, s.shop_name, i.item_name, i.item_sell_price, i.item_option_state, l.location_name, m.media_file_name, m.media_file_extension, m.media_file_path from items as i"
			+ "left join item_category as c on i.item_category_id = c.category_id left join shops as s on i.shops_shop_id  = s.shop_id "
			+ "join locations as l on s.LOCATIONS_LOCATION_ID =l.location_id "
			+ "join media as m on i.media_media_id = m.media_id "
			+ "where ${field} like '%${query}%' limit 100")
	List<ItemForList> searchItemList(@Param("field")String field, @Param("query")String query);

	@Select("select * from items where item_path_url = #{path_url} limit 1")
	Item findItemIdByPathUrl(String path_url);

	@Select("select count(*) from items")
	int countItems();

	@Insert("insert into shipping_day (items_item_id, day_mon, day_tue, day_wed, day_thu, day_fri, day_sat, day_sun, day_send_time) "
			+ " values (#{item_id}, #{day_mon}, #{day_tue}, #{day_wed}, #{day_thu}, #{day_fri}, #{day_sat}, #{day_sun}, #{day_send_time})")
	void insertItemShippingDayWithDays(ShippingDays shippingDays);

	@Insert("insert into shipping_day (day_send_day) values(#{day_send_day})")
	void insertItemIntoShippingDay(AddItem item);

	@Insert("insert into shipping_price (items_item_id, price_group_id, price_price, price_desc) "
			+ "values <foreach collection='price_options' item='priceOption'  separator=', ' > "
			+ "(#{items_item_id}, #{priceOption.index}, #{priceOption.price}, #{priceOption.desc}) "
			+ "</foreach>")
	void insertItemShippingPriceWithShippingPrice(ShippingPrice shippingPrice);

	@Insert("insert into item_option (items_item_id,option_tag, option_title, option_row_id ,option_row_value,option_row_price) "
			+ "values <foreach collection=\"optionBlocks\" item=\"block\" separator=\", \" index=\"opt_id\"> "
			+ "<foreach collection=\"block.optionRow\" item=\"row\" separator=\", \" index=\"i\"> "
			+ "( #{items_item_id}, #{opt_id}, #{block.optionTitle}, #{i}, #{row.description}, #{row.price} ) "
			+ "</foreach> </foreach>")
	void insertItemOptions(ItemOptionManage iom);

	@Select("select * from item_category")
	List<Category> selectCatetoryListAll();

	@Insert("insert into item_category(category_name) values (#{category_name})")
	void insertCategory(Category category);

	@Select("select * from item_category where category_id = #{category_id}")
	Category selectCategoryById(int category_id);

	@Update("update item_category set category_name = #{category_name} where category_id = #{category_id}")
	void updateCategoryById(Category category);

	@Delete("delete from item_category where category_id = #{category_id}")
	void deleteCategoryById(int category_id);

}
