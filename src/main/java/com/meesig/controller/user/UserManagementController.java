package com.meesig.controller.user;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.meesig.model.User;
import com.meesig.service.UserManager;
import com.meesig.util.Paging;

@Controller
@RequestMapping("/user")
public class UserManagementController {
    private static final Logger LOG = LoggerFactory.getLogger(UserManagementController.class);

	@Autowired
	UserManager userManager;

    @RequestMapping(value="/list", method=RequestMethod.GET)
    public String userList(Model model,@RequestParam(defaultValue="1", required=false) int page, @RequestParam(defaultValue="20", required=false) int count) {
    	if ( count>100)	{ count = 100; 	}
    	if ( count<10 )	{ count = 10; 	}
    	if ( page<1   )	{ page =1; 		}
    	
    	List<User> userList = userManager.selectSortedUserList(count, page);
    	Paging paging = new Paging(page, userManager.getTotalUser(), count);
    	
    	model.addAttribute("userList", userList);
    	model.addAttribute("paging", paging);
        return "user/list";
    }
    
    @RequestMapping(value="/list/search", method=RequestMethod.GET)
    public String userListSearch(Model model, @RequestParam String query, 
    										  @RequestParam(defaultValue="name") String type) {
    	
    	if(query.equals("")){
    		return "redirect:/user/list";
    	}
    	
    	List<User> userList = userManager.searchUserList(type, query);
    	model.addAttribute("userList", userList);
    	
    	if(userList.size()>=100){
    		model.addAttribute("msg", "검색결과가 너무 많습니다. 전부 출력되지 않을 수 있습니다.");
    	}
    	
        return "user/list";
    }

    @RequestMapping("/add")
    public String userAdd(Model model) {
    	
    	model.addAttribute("user", new User());
        return "user/add";
    }
    
    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String createUser(@Valid User user, BindingResult bindingResult, Model model){
    	LOG.debug("User : {}",user);
		
		if(bindingResult.hasErrors()){
			List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError error : errors){
				LOG.info("error : {}", error.getDefaultMessage());
			}
			return "user/add";
		}
		
		if(userManager.isExistentUser(user)){
			model.addAttribute("msg", "이미 존재하는 사용자입니다.");
			return "user/add";
		}
		
		userManager.createUserInAdminPage(user);
		user = new User();
		model.addAttribute("msg", "사용자를 추가하였습니다.");
		return "user/add";
    }
    
    
    @RequestMapping("/detail/{user_id}")
    public String userDetail(@PathVariable int user_id, Model model) {
		
    	model.addAttribute("user", userManager.findUserById(user_id));
    	
    	return "user/detail";
    }
    
    @RequestMapping(value="/edit", method=RequestMethod.POST)
    public String editUser(User user, Model model){
		if(userManager.updateUserInfo(user)){
			model.addAttribute("msg", "사용자정보를 변경하였습니다.");
		}else{
			model.addAttribute("msg", "사용자정보를 변경실패.");
		}
		model.addAttribute("user", userManager.findUserById(user.getUser_id()));
		return "user/detail";
    }
}
