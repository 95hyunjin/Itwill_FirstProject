package com.itwillbs.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Inject
	private MemberService mService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET() {
		logger.debug(" registerGET() 호출");
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(MemberVO vo,Model model) throws Exception{
		logger.debug(" loginPOST() 호출");
		String id = mService.memberLogin(vo);
		
		if(!id.isEmpty()) {
			model.addAttribute("id", id);
			
			return "redirect:/main";
		}
		
		return "/login";
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(MemberVO vo) throws Exception{
		logger.debug(" registerPOST() 호출");
		logger.debug(" memberVO : "+vo);
		
		mService.memberInsert(vo);
		
		return "redirect:/login";
	}
	
	
	
} // controller
