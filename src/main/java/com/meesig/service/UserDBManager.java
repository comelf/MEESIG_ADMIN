package com.meesig.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meesig.mapper.UserMapper;
import com.meesig.model.User;

import core.util.auth.Crc32;
import core.util.auth.PhPass;

@Repository
public class UserDBManager {
	
	@Autowired
    UserMapper userMapper;

	@Autowired
	Crc32 crc;
	
	@Autowired
	PhPass phPass;
	
	private int total = 0;
	private static final int SQL_SUCSSES = 1;
	
	public void createUser(User user, String role) {
		user.setUser_crc_id( crc.getCode(user.getUser_login_id()));
		user.setUser_role(role);
		user.setUser_password(phPass.HashPassword(user.getUser_password()));
		userMapper.createUser(user);
	}

	public User selectUserByLoginIdAndCrc(User user) {
		user.setUser_crc_id(userIdToCrc(user.getUser_login_id()));
		return userMapper.selectOneUserByIdAndCrc(user);
	}

	private int userIdToCrc(String user_login_id) {
		return crc.getCode(user_login_id);
	}

	public void deleteUserById(User deleteUser) {
		deleteUser.setUser_crc_id(userIdToCrc(deleteUser.getUser_login_id()));
		userMapper.deleteUser(deleteUser);
	}

	public boolean isMatchPassword(User adminUser, String user_pw) {
		if(adminUser==null){
			return false;
		}
		return phPass.CheckPassword(user_pw, adminUser.getUser_password());
	}

	public List<User> selectSortedUserList(int count, int page) {
		total = countTotalUser();
		return userMapper.selectSortedUserListForManagement((page-1)*count, count);
	}

	public int countTotalUser() {
		return userMapper.countTotalUser();
	}

	public boolean isExistentUser(User user) {
		User exist = selectUserByLoginIdAndCrc(user);
		if(exist!=null){
			return true;
		}else{
			return false;
		}
	}

	public void createUserInAdminPage(User user) {
		user.setUser_crc_id( crc.getCode(user.getUser_login_id()));
		user.setUser_password(phPass.HashPassword(user.getUser_password()));
		userMapper.createUserInAdminPage(user);
		
	}

	public int getTotalUser() {
		return total;
	}

	public List<User> searchUserList(String type, String query) {
		String field = "";
		
		switch (type) {
			case "name":
				field = "user_name";
				break;
			case "id":
				field =  "user_login_id";
				break;
			case "email":
				field = "user_email";
				break;
			default:
				return null;
		}
		
		return userMapper.searchUserList(field, query);
	}

	public User findUserById(int user_id) {
		return userMapper.findUserById(user_id);
	}

	public boolean updateUserInfo(User user) {
		
		if(!user.getUser_password().equals("")){
			user.setUser_password(phPass.HashPassword(user.getUser_password()));
		}
			
		if(user.getUser_gender().isEmpty()){
			return false;
		}
		
		if(userMapper.updateUserInfoById(user) == SQL_SUCSSES){
			return true;
		}
		return false;
	}
}
