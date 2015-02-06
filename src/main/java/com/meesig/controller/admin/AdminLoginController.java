package com.meesig.controller.admin;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import core.auth.AdminDetails;

@Controller
@RequestMapping("admin")
public class AdminLoginController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminLoginController.class);

    @RequestMapping("/login")
    public String adminLogin(@RequestParam(defaultValue="0", required=false) int login_error, Model model) {
    	
    	if(login_error==1){
    		model.addAttribute("msg", "아이디나 비밀번호가 잘못되었습니다.");
    	}
    	
        return "admin/login";
    }

    @RequestMapping("/loginSuccess")
    public String adminLoginSuccess(HttpSession session) {
        AdminDetails userDetails = (AdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        session.setAttribute("userLoginInfo", userDetails);
        return "redirect:/";
    }

    @RequestMapping("/logout")
     public String adminLogout(HttpSession session) {
        AdminDetails userDetails = (AdminDetails)session.getAttribute("userLoginInfo");
        session.invalidate();
        return "admin/login";
    }



}
