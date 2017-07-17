package com.hp.gdcc.tsportal.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hp.gdcc.tsportal.service.TestService;

@RequestMapping
@Controller
public class IndexController {

	@Autowired
	private TestService testService;
	
	@RequestMapping
	public String index(){
		return "index";
	}
	
	@RequestMapping("test")
	@ResponseBody
	public String index(Long id){
		return testService.selectByPrimaryKey(id).getValue();
	}
	
}
