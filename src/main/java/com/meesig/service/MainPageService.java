package com.meesig.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meesig.mapper.MainMapper;
import com.meesig.model.Banner;
import com.meesig.model.MainRow;
import com.meesig.model.MainItem;

@Service
public class MainPageService {

	@Autowired
	private MainMapper mainMapper;

	public MainPageService() {
		
	}
	
	private List<MainRow> loadContentsFromDatabase() {
		return mainMapper.selectAllForMainPage();
	}
	
	public List<Banner> getWebMainBannerList() {
		return mainMapper.selectWebBannerList();
	}

	public List<Banner> getMobileMainBannerList() {
		return mainMapper.selectMobileBannerList();
	}

	public void insertBanner(Banner banner) {
		mainMapper.insertBanner(banner);
	}

	public void updateBanner(Banner banner) {
		mainMapper.updateBanner(banner);
	}

	public List<MainItem> getRecommendList() {
		return mainMapper.selectAllRecommend();
	}

	public MainItem getWeeklyBest() {
		return mainMapper.selectWeeklyBest();
	}

	public MainItem getSomethingNew() {
		return mainMapper.selectSomethingNew();
	}

	public void updateRecommend(MainItem recommend) {
		mainMapper.updateRecommend(recommend);
		
	}

	public void updateRecTop(MainItem recommend) {
		mainMapper.updateRecommendTop(recommend);
	}

	public List<MainItem> getReviews() {
		return mainMapper.selectReviews();
	}

	public void insertReview(MainItem review) {
		mainMapper.insertReview(review);
	}

	public void updateReview(MainItem review) {
		mainMapper.updateReview(review);
	}

	public List<MainItem> getMenus() {
		return mainMapper.selectMenus();
	}

	public void insertMainMenu(MainItem menu) {
		mainMapper.insertMainMenu(menu);
	}

	public void updateMainMenu(MainItem menu) {
		mainMapper.updateMainMenu(menu);
	}
}
