package com.meesig.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meesig.mapper.MainMapper;
import com.meesig.model.Banner;
import com.meesig.model.MainRow;

@Service
public class MainPageService {

	@Autowired
	private MainMapper mainMapper;

	public MainPageService() {
		
	}
	
	private List<MainRow> loadContentsFromDatabase() {
		return mainMapper.selectAllForMainPage();
	}
	
	public List<Banner> getMainBannerList() {
		return mainMapper.selectBannerList();
	}


}
