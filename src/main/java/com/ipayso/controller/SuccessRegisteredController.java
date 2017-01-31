package com.ipayso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SuccessRegisteredController {
	
	@RequestMapping("/success-registered")
	public String sucess(){
		return "/success-registered";
	}
}
