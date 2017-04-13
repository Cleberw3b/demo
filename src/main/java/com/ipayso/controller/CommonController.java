package com.ipayso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController.class -> This Controller offers a URL filter to map common requests
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Controller
 */
@Controller
public class CommonController {
	
	/**
	 * Map requests from '', /, /home to render home view
	 * @return home page view
	 */
    @RequestMapping({"''","/","/home"})
    public String index(){
        return "home";
    }
    
    /**
     * Map request from /badUser to its view
     */
    @RequestMapping("/badUser")
    public String badUser(){
    	return "badUser";
    }
}