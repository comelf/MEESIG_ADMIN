package com.meesig.controller.tab;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meesig.model.Banner;
import com.meesig.service.MainPageService;

@Controller
@RequestMapping("/main")
public class MainPageManagementController {
    private static final Logger LOG = LoggerFactory.getLogger(UserManagementController.class);

    @Autowired
    MainPageService mainService;

    @RequestMapping(value="/banner", method=RequestMethod.GET)
    public String bannerList(Model model) {
    	List<Banner> banners =  mainService.getMainBannerList();
    	model.addAttribute("banners", banners);
        return "main/banner";
    }
    
    @RequestMapping(value="/recommand", method=RequestMethod.GET)
    public String recommandList() {
    	
    	
    	
        return "user/list";
    }
    
    @RequestMapping(value="/review", method=RequestMethod.GET)
    public String reviewList() {
    	
    	
    	
        return "user/list";
    }
    
}
