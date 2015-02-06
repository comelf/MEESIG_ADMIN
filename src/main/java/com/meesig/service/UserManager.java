package com.meesig.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meesig.model.User;
import com.meesig.model.UserListTable;

import core.util.auth.Crc32;
import core.util.auth.PhPass;

@Service
public class UserManager {
	
	@Autowired
    SqlSession sqlSession;

	@Autowired
	Crc32 crc;
	
	@Autowired
	PhPass phPass;
	
	private int total;
	
	private static final Logger log = LoggerFactory
			.getLogger(UserManager.class);
	
	public void createUser(User user, String role) {
		user.setUser_crc_id( crc.getCode(user.getUser_login_id()));
		user.setUser_role(role);
		user.setUser_password(phPass.HashPassword(user.getUser_password()));
		sqlSession.insert("UserMapper.createUser", user);
	}

	public int selectOne() {
		return sqlSession.selectOne("UserMapper.selectOne");
	}

	public User selectUserByIdAndCrc(User user) {
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
		User exist = selectUserByIdAndCrc(user);
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
}
