package com.meesig.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

import com.meesig.model.Banner;
import com.meesig.model.MainRow;
import com.meesig.model.Media;
import com.meesig.model.MainItem;

public interface MainMapper {

	@Select("select m.main_key, m.main_order, m.main_link_url, m.main_text, m.main_sub, i.item_name,i.item_path_url, i.item_description, i.item_sell_price, i.item_sale_price, i.item_new_menu_state, i.item_option_state, i.item_shipping_day_state, e.media_file_path, e.media_file_name, e.media_file_extension "
			+ "from MAIN as m "
			+ "join ITEMS as i on m.ITEMS_item_id = i.item_id "
			+ "join media as e on i.media_media_id = e.media_id "
			+ "order by main_key, main_order")
	List<MainRow> selectAllForMainPage();

	@Select("select row_id as slide_id, main_key as slide_type, main_order as slide_order, main_link_url as slide_href, main_text as slide_src, main_sub as slide_des,MEDIA_media_id  from MAIN "
			+ "where main_key <= 2 order by main_order")
	List<Banner> selectAllBannerList();

	@Select("select row_id as slide_id, main_key as slide_type, main_order as slide_order, main_link_url as slide_href, main_text as slide_src, main_sub as slide_des, MEDIA_media_id from MAIN "
			+ "where main_key = 1 order by main_order")
	List<Banner> selectWebBannerList();
	
	@Select("select row_id as slide_id, main_key as slide_type, main_order as slide_order, main_link_url as slide_href, main_text as slide_src, main_sub as slide_des, MEDIA_media_id from MAIN "
			+ "where main_key = 2 order by main_order")
	List<Banner> selectMobileBannerList();

	@Insert("insert into MAIN( main_key, main_order, main_link_url, main_text, main_sub,MEDIA_media_id) values(#{slide_type}, #{slide_order}, #{slide_href},#{slide_src}, #{slide_des}, #{MEDIA_media_id})")
	void insertBanner(Banner banner);

	@Update("update MAIN set  main_key = #{slide_type}, main_order = #{slide_order}, main_link_url = #{slide_href}, main_text = #{slide_src}, main_sub = #{slide_des}, MEDIA_media_id = #{MEDIA_media_id} where row_id = #{slide_id}")
	void updateBanner(Banner banner);

	@Select("select * from MAIN where main_key = 5 order by main_order")
	@Results(value ={
			@Result(property="media_media_id", column="media_media_id"),
			@Result(property="media", column="media_media_id", javaType=Media.class, one=@One(select=("com.meesig.mapper.MediaMapper.selectMediaByMediaId")))
	})
	List<MainItem> selectAllRecommend();

	@Select("select * from MAIN where main_key = 3")
	@Results(value ={
			@Result(property="media_media_id", column="media_media_id"),
			@Result(property="media", column="media_media_id", javaType=Media.class, one=@One(select=("com.meesig.mapper.MediaMapper.selectMediaByMediaId")))
	})
	MainItem selectWeeklyBest();

	@Select("select * from MAIN where main_key = 4")
	@Results(value ={
			@Result(property="media_media_id", column="media_media_id"),
			@Result(property="media", column="media_media_id", javaType=Media.class, one=@One(select=("com.meesig.mapper.MediaMapper.selectMediaByMediaId")))
	})
	MainItem selectSomethingNew();

	@Update("update MAIN set media_media_id =#{media_media_id}, items_item_id=#{items_item_id}, main_order = #{main_order}, main_text = #{main_text},  main_sub = #{main_sub} where row_id = #{row_id}")
	void updateRecommend(MainItem recommend);

	@Update("update MAIN set media_media_id =#{media_media_id}, items_item_id=#{items_item_id} where row_id = #{row_id}")
	void updateRecommendTop(MainItem recommend);

	@Select("select * from MAIN where main_key = 6 order by main_order")
	@Results(value ={
			@Result(property="media_media_id", column="media_media_id"),
			@Result(property="media", column="media_media_id", javaType=Media.class, one=@One(select=("com.meesig.mapper.MediaMapper.selectMediaByMediaId")))
	})
	List<MainItem> selectReviews();

	@Insert("insert into MAIN ( main_key, media_media_id, main_order, items_item_id, main_text,main_sub) values(#{main_key}, #{media_media_id}, #{main_order},#{items_item_id}, #{main_text}, #{main_sub})")
	void insertReview(MainItem review);

	@Update("update MAIN set main_key= #{main_key}, media_media_id = #{media_media_id}, main_order =#{main_order}, items_item_id = #{items_item_id}, main_text =#{main_text},main_sub = #{main_sub} where row_id = #{row_id}")
	void updateReview(MainItem review);

	@Select("select * from MAIN where main_key = 7 order by main_order")
	@Results(value ={
			@Result(property="media_media_id", column="media_media_id"),
			@Result(property="media", column="media_media_id", javaType=Media.class, one=@One(select=("com.meesig.mapper.MediaMapper.selectMediaByMediaId")))
	})
	List<MainItem> selectMenus();

	@Insert("insert into MAIN ( main_key, media_media_id, main_order, items_item_id) values(#{main_key}, #{media_media_id}, #{main_order},#{items_item_id})")
	void insertMainMenu(MainItem menu);
	
	@Update("update MAIN set main_key= #{main_key}, media_media_id = #{media_media_id}, main_order =#{main_order}, items_item_id = #{items_item_id} where row_id = #{row_id}")
	void updateMainMenu(MainItem menu);

}
