package com.meesig.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.meesig.model.User;

public interface UserMapper {
	
	@Insert("insert into USERS (user_login_id, user_crc_id, user_password, user_name, user_email, user_b_date, user_gender, user_role, user_location)  "
			+"values (#{user_login_id}, #{user_crc_id}, #{user_password}, #{user_name}, #{user_email}, #{user_b_date}, #{user_gender}, #{user_role}, #{user_location})")
	void createUser(User user);
	
	@Select("select * from USERS where user_crc_id = #{user_crc_id} and user_login_id = #{user_login_id}")
	User selectOneUserByIdAndCrc(User user);
	
	@Delete("delete from users where user_crc_id = #{user_crc_id} and user_login_id = #{user_login_id}")
	void deleteUser(User user);
	
	@Select("select count(user_id) from users")
	int countTotalUser();
	
	@Select("select user_id,user_login_id,user_name,user_email,user_b_date,user_gender,user_join_date,user_status,user_point,user_grade,user_role,user_location,user_enabled "
			+ "from users order by user_id DESC LIMIT #{start}, #{count}")
	List<User> selectSortedUserListForManagement(@Param("start")int start, @Param("count")int count);
	
	
	@Select("select * from users where ${field} like '%${query}%' limit 100")
	List<User> searchUserList(@Param("field")String field,@Param("query")String query);
	
	@Insert("insert into USERS (user_login_id, user_crc_id, user_password, user_name, user_email, user_gender, user_role, user_location) "
			+ "values (#{user_login_id}, #{user_crc_id}, #{user_password}, #{user_name}, #{user_email}, #{user_gender}, #{user_role}, #{user_location})")
	void createUserInAdminPage(User user);
	
	@Select("select user_id,user_login_id,user_name,user_email,user_b_date,user_gender,user_join_date,user_exit_date,user_status,user_point,user_grade,user_role,user_location,user_enabled "
			+ "from users where user_id = #{user_id}")
	User findUserById(int user_id);
	
	@Update("update users set user_password = #{user_password}, user_name = #{user_name}, user_email = #{user_email}, "
			+ "user_gender = #{user_gender}, user_role =#{user_role}, user_location = #{user_location} ,user_status "
			+ "= #{user_status}, user_point = #{user_point}, user_enabled = #{user_enabled} where user_id = ${user_id}")
	int updateUserInfoById(User user);

}
