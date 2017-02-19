package com.ipayso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController.class -> This Controller offers a URL filter to map requests for home page 
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Controller
 */
@Controller
public class HomeController {

	/**
	 * Map requests from '', /, /home to render home view
	 * @return home page view
	 */
    @RequestMapping({"''","/","/home"})
    public String index(){
        return "home";
    }
}