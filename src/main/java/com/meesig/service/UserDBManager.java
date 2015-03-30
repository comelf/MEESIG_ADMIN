package com.meesig.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meesig.model.User;

import core.util.auth.Crc32;
import core.util.auth.PhPass;

@Repository
public class UserDBManager {
	
	@Autowired
    SqlSession sqlSession;

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
		sqlSession.insert("UserMapper.createUser", user);
	}

	public int selectOne() {
		return sqlSession.selectOne("UserMapper.selectOne");
	}

	public User selectUserByLoginIdAndCrc(User user) {
		user.setUser_crc_id(userIdToCrc(user.getUser_login_id()));
		return sqlSession.selectOne("UserMapper.selectOneUserByIdAndCrc", user);
	}

	private int userIdToCrc(String user_login_id) {
		return crc.getCode(user_login_id);
	}

	public void deleteUserById(User deleteUser) {
		deleteUser.setUser_crc_id(userIdToCrc(deleteUser.getUser_login_id()));
		sqlSession.delete("UserMapper.deleteUser",deleteUser);
	}

	public boolean isMatchPassword(User adminUser, String user_pw) {
		if(adminUser==null){
			return false;
		}
		return phPass.CheckPassword(user_pw, adminUser.getUser_password());
	}

	public List<User> selectSortedUserList(int count, int page) {
		total = countTotalUser();
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("start", (page-1)*count);
		queryMap.put("count", count);

		return sqlSession.selectList("UserMapper.selectSortedUserListForManagement", queryMap );
	}

	public int countTotalUser() {
		return sqlSession.selectOne("UserMapper.countTotalUser");
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
		
		sqlSession.insert("UserMapper.createUserInAdminPage", user);
		
	}

	public int getTotalUser() {
		return total;
	}

	public List<User> searchUserList(String type, String query) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		
		switch (type) {
			case "name":
				queryMap.put("type", "user_name");
				break;
			case "id":
				queryMap.put("type", "user_login_id");
				break;
			case "email":
				queryMap.put("type", "user_email");
				break;
			default:
				return null;
		}
		queryMap.put("query", query);
		
		return sqlSession.selectList("UserMapper.searchUserList", queryMap );
	}

	public User findUserById(int user_id) {
		return sqlSession.selectOne("UserMapper.findUserById", user_id);
	}

	public boolean updateUserInfo(User user) {
		
		if(!user.getUser_password().equals("")){
			user.setUser_password(phPass.HashPassword(user.getUser_password()));
		}

		if(sqlSession.update("UserMapper.updateUserInfoById", user) == SQL_SUCSSES){
			return true;
		}
		return false;
	}
}
