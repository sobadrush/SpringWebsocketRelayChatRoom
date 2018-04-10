package com.ctbc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ctbc.model.UserVO;

@Controller
public class HomeController {

	public HomeController() {
		System.out.println("=== new HomeController ===");
	}

	@RequestMapping(value = "/")
	public String init() {
		System.out.println("login page");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, UserVO userVO) {
		System.out.println(userVO.toString());
		request.setAttribute("userName", userVO.getUserName());
		return "index";
	}
}
