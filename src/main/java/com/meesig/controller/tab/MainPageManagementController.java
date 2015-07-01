package com.meesig.controller.tab;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.meesig.model.Banner;
import com.meesig.model.MainItem;
import com.meesig.service.MainPageService;

@Controller
@RequestMapping("/main")
public class MainPageManagementController {
    private static final Logger LOG = LoggerFactory.getLogger(UserManagementController.class);

    @Autowired
    MainPageService mainService;

    @RequestMapping(value="/webbanner", method=RequestMethod.GET)
    public String webBannerList(Model model) {
    	List<Banner> webBanners =  mainService.getWebMainBannerList();
    	model.addAttribute("webBanners", webBanners);
        return "main/webBanner";
    }
   
    @RequestMapping(value="/webbanner/create", method=RequestMethod.POST)
    public String webBannerUpdate(@RequestParam("slide_id") int[] slide_id,
    							  @RequestParam("slide_type") int[] slide_type,
    							  @RequestParam("slide_order") int[] slide_order,
    							  @RequestParam("media_id") int[] media_id,
    							  @RequestParam("slide_src") String[] slide_src,
    							  @RequestParam("slide_href") String[] slide_href,
    							  @RequestParam("slide_des") String[] slide_des,
    							  Model model) {
    	
    	for(int i=0; i<slide_id.length; i++){
    		Banner banner = new Banner();
    		banner.setSlide_id(slide_id[i]);
    		banner.setSlide_type(slide_type[i]);
    		banner.setMEDIA_media_id(media_id[i]);
    		banner.setSlide_order(slide_order[i]);;
    		banner.setSlide_src(slide_src[i]);
    		banner.setSlide_href(slide_href[i]);
    		banner.setSlide_des(slide_des[i]);
    		
    		if(banner.getSlide_id()<1){
    			mainService.insertBanner(banner);
    		}else{
    			mainService.updateBanner(banner);
    		}
    	}
    	
    	List<Banner> webBanners =  mainService.getWebMainBannerList();
    	model.addAttribute("webBanners", webBanners);
        return "main/webBanner";
    }
    
    @RequestMapping(value="/mobilebanner/create", method=RequestMethod.POST)
    public String mobileBannerUpdate(@RequestParam("slide_id") int[] slide_id,
    							  @RequestParam("slide_type") int[] slide_type,
    							  @RequestParam("slide_order") int[] slide_order,
    							  @RequestParam("media_id") int[] media_id,
    							  @RequestParam("slide_src") String[] slide_src,
    							  @RequestParam("slide_href") String[] slide_href,
    							  @RequestParam("slide_des") String[] slide_des,
    							  Model model) {
    	
    	for(int i=0; i<slide_id.length; i++){
    		Banner banner = new Banner();
    		banner.setSlide_id(slide_id[i]);
    		banner.setSlide_type(slide_type[i]);
    		banner.setMEDIA_media_id(media_id[i]);
    		banner.setSlide_order(slide_order[i]);;
    		banner.setSlide_src(slide_src[i]);
    		banner.setSlide_href(slide_href[i]);
    		banner.setSlide_des(slide_des[i]);
    		
    		if(banner.getSlide_id()<1){
    			mainService.insertBanner(banner);
    		}else{
    			mainService.updateBanner(banner);
    		}
    	}
    	
    	List<Banner> webBanners =  mainService.getMobileMainBannerList();
    	model.addAttribute("mobileBanners", webBanners);
        return "main/mobileBanner";
    }
    @RequestMapping(value="/mobilebanner", method=RequestMethod.GET)
    public String mobileBannerList(Model model) {
    	List<Banner> mobileBanners =  mainService.getMobileMainBannerList();
    	model.addAttribute("mobileBanners", mobileBanners);
        return "main/mobileBanner";
    }
    
    @RequestMapping(value="/recommend", method=RequestMethod.GET)
    public String recommendList(Model model) {
    	List<MainItem> recommends = mainService.getRecommendList();
    	MainItem wBest = mainService.getWeeklyBest();
    	MainItem sNew = mainService.getSomethingNew();
    	model.addAttribute("recommends", recommends);
    	model.addAttribute("wBest", wBest);
    	model.addAttribute("sNew", sNew);
        return "main/recommend";
    }
    
    @RequestMapping(value="/recommend/update", method=RequestMethod.POST)
    public String recommendListUpdate(@RequestParam("row_id")int[] rowId,
							    	  @RequestParam("main_key")int[] mainKey,
							    	  @RequestParam("item_id")int[] itemId,
							    	  @RequestParam("media_id")int[] mediaId,
							    	  @RequestParam("main_order")int[] mainOrder,
							    	  @RequestParam("main_text")String[] mainText,
							    	  @RequestParam("main_sub")String[] mainSub,
							    	  Model model) {
    	
    	for(int i =0; i<rowId.length; i++){
    		MainItem recommend = new MainItem();
    		recommend.setRow_id(rowId[i]);
    		recommend.setMain_key(mainKey[i]);
    		recommend.setItems_item_id(itemId[i]);
    		recommend.setMedia_media_id(mediaId[i]);
    		recommend.setMain_order(mainOrder[i]);
    		recommend.setMain_text(mainText[i]);
    		recommend.setMain_sub(mainSub[i]);
    		mainService.updateRecommend(recommend);
    	}
    	
    	
    	List<MainItem> recommends = mainService.getRecommendList();
    	MainItem wBest = mainService.getWeeklyBest();
    	MainItem sNew = mainService.getSomethingNew();
    	model.addAttribute("recommends", recommends);
    	model.addAttribute("wBest", wBest);
    	model.addAttribute("sNew", sNew);
    	return "main/recommend";
    }
    
    
	@RequestMapping(value="/rectop/update", method=RequestMethod.POST)
    public String recommendTopUpdate(@RequestParam("row_id")int[] rowId,
							    	  @RequestParam("main_key")int[] mainKey,
							    	  @RequestParam("item_id")int[] itemId,
							    	  @RequestParam("media_id")int[] mediaId,
							    	  Model model) {
    	
    	for(int i =0; i<rowId.length; i++){
    		MainItem recommend = new MainItem();
    		recommend.setRow_id(rowId[i]);
    		recommend.setMain_key(mainKey[i]);
    		recommend.setItems_item_id(itemId[i]);
    		recommend.setMedia_media_id(mediaId[i]);
    		mainService.updateRecTop(recommend);
    	}
    	
    	
    	List<MainItem> recommends = mainService.getRecommendList();
    	MainItem wBest = mainService.getWeeklyBest();
    	MainItem sNew = mainService.getSomethingNew();
    	model.addAttribute("recommends", recommends);
    	model.addAttribute("wBest", wBest);
    	model.addAttribute("sNew", sNew);
    	return "main/recommend";
    }
    
    @RequestMapping(value="/review", method=RequestMethod.GET)
    public String reviewList(Model model) {
    	List<MainItem> reviews = mainService.getReviews();
    	model.addAttribute("reviews", reviews);
        return "main/review";
    }
    
    @RequestMapping(value="/review/update", method=RequestMethod.POST)
    public String reviewUpdate(@RequestParam("row_id")int[] rowId,
					    	  @RequestParam("main_key")int[] mainKey,
					    	  @RequestParam("media_id")int[] mediaId,
					    	  @RequestParam("main_order")int[] mainOrder,
					    	  @RequestParam("item_id")int[] itemId,
					    	  @RequestParam("main_text")String[] mainText,
					    	  @RequestParam("main_sub")String[] mainSub,
							  Model model) {
    	
    	for(int i =0; i<rowId.length; i++){
    		MainItem review = new MainItem();
    		review.setRow_id(rowId[i]);
    		review.setMain_key(mainKey[i]);
    		review.setMain_order(mainOrder[i]);
    		review.setItems_item_id(itemId[i]);
    		review.setMedia_media_id(mediaId[i]);
    		review.setMain_text(mainText[i]);
    		review.setMain_sub(mainSub[i]);
    		if(review.getRow_id() < 1){
    			mainService.insertReview(review);
    		}else{
    			mainService.updateReview(review);
    		}
    	}
    	
    	
    	List<MainItem> reviews = mainService.getReviews();
    	model.addAttribute("reviews", reviews);
    	return "main/review";
    }
    
    @RequestMapping(value="/menus", method=RequestMethod.GET)
    public String menuList(Model model) {
    	List<MainItem> menus = mainService.getMenus();
    	model.addAttribute("menus", menus);
        return "main/menus";
    }
    
	@RequestMapping(value="/menus/update", method=RequestMethod.POST)
    public String menusUpdate(@RequestParam("row_id")int[] rowId,
					    	  @RequestParam("main_key")int[] mainKey,
					    	  @RequestParam("media_id")int[] mediaId,
					    	  @RequestParam("main_order")int[] mainOrder,
					    	  @RequestParam("item_id")int[] itemId,
							  Model model) {
    	
    	for(int i =0; i<rowId.length; i++){
    		MainItem menu = new MainItem();
    		menu.setRow_id(rowId[i]);
    		menu.setMain_key(mainKey[i]);
    		menu.setMain_order(mainOrder[i]);
    		menu.setItems_item_id(itemId[i]);
    		menu.setMedia_media_id(mediaId[i]);
    		if(menu.getRow_id() < 1){
    			mainService.insertMainMenu(menu);
    		}else{
    			mainService.updateMainMenu(menu);
    		}
    	}
    	
    	List<MainItem> menus = mainService.getMenus();
    	model.addAttribute("menus", menus);
        return "main/menus";
    }
}
