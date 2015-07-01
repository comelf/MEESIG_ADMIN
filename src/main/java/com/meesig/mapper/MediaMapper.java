package com.meesig.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.meesig.model.Media;
import com.meesig.model.PhotoInfo;

public interface MediaMapper {
	@Insert("INSERT INTO media (media_file_name, media_file_extension, media_file_path, media_origin_name, media_type) "
			+ "VALUES (#{uuFileName}, #{fileExt}, #{requestPath}, #{oriFileName}, 1)")
	@SelectKey(keyProperty = "media_id", before = false, resultType = int.class, statement = { "SELECT @@IDENTITY" })
	int insertPhoto(PhotoInfo pi);
	
	@Select("select * from media where media_id = #{media_id}")
	Media selectMediaByMediaId(@Param("media_id")int media_id);
}
