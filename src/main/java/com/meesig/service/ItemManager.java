package com.meesig.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meesig.model.ItemForList;

@Service
public class ItemManager {

	@Autowired
    SqlSession sqlSession;

	private int total = 0;
	
	public List<ItemForList> selectSortedItemList(String sort, int count, int page) {
		total = countTotalItem();
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("start", (page-1)*count);
		queryMap.put("count", count);
		
		return sqlSession.selectList("ItemMapper.selectSortedItemListForManagement", queryMap);
	}

	private int countTotalItem() {
		return sqlSession.selectOne("ItemMapper.countTotalItem");
	}

	public int getTotalUser() {
		return this.total;
	}

	public Object findItemByIdForManagement(int item_id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
