package com.meesig.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DashboardController {
    private static final Logger LOG = LoggerFactory.getLogger(DashboardController.class);

    @RequestMapping("/")
    public String adminIndex(Model model) {
        LOG.debug("로그인!!");

        Map<String, String> map = new HashMap<String, String>();
        map.put("items", "1");
        map.put("news","10");
        map.put("orders","1120");
        map.put("users","100/100000");

        model.addAttribute("dash", map);
        return "admin/admin";
    }
    
    @RequestMapping("/denied")
    public String accessDenied() {
        return "admin/denied";
    }
    
    
    
}
