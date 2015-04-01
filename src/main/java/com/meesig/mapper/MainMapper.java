package com.meesig.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.meesig.model.Banner;
import com.meesig.model.MainRow;

public interface MainMapper {

	@Select("select m.main_key, m.main_order, m.main_link_url, m.main_text, m.main_sub, i.item_name,i.item_path_url, i.item_description, i.item_sell_price, i.item_sale_price, i.item_new_menu_state, i.item_option_state, i.item_shipping_day_state, e.media_file_path, e.media_file_name, e.media_file_extension "
			+ "from MAIN as m "
			+ "join ITEMS as i on m.ITEMS_item_id = i.item_id "
			+ "join media as e on i.media_media_id = e.media_id "
			+ "order by main_key, main_order")
	List<MainRow> selectAllForMainPage();

	@Select("select row_id as slide_id, main_order as slide_order, main_link_url as slide_href, main_text as slide_src, main_sub as slide_des  from MAIN "
			+ "where main_key = 1 order by main_order")
	List<Banner> selectBannerList();
	
}
