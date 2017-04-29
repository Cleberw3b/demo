package com.ipayso.controller.admin;

import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * AdminController<T>.class -> This is an interface to sign what Controllers should do
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Controller
 */
public interface AdminController<T> {
	
	/**
	 * All GET methods should be pageable and get message variable from path it exists
	 * @param pageable
	 * @param msg
	 * @return ModelAndView
	 * @see Pageable
	 */
	@RequestMapping(method = RequestMethod.GET)
	ModelAndView list(Pageable pageable, @RequestParam(required = false, value = "msg") String msg);
	
	/**
	 * All POST methods must have an Object that has the same type of <T> and their respective bind result and redirect attributes
	 * @param object
	 * @param result
	 * @param attributes
	 * @return ModelAndView
	 * @see BindingResult
	 * @see RedirectAttributes
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(T object, BindingResult result, RedirectAttributes attributes, WebRequest request);
	
	/**
	 * This method will perform creation of an object of type <T>
	 * @param object
	 * @return ModelAndView
	 */
	@RequestMapping("/add")
	public ModelAndView add(T object, WebRequest request);
	
	/**
	 * This method will call the view to edit the object of type <T>
	 * @param id
	 * @return ModelAndView
	 */
	@RequestMapping("/edit/{id}")
    public ModelAndView edit(Integer id, WebRequest request);
	
	/**
	 * This method will delete the object of type <T>
	 * @param id
	 * @return ModelAndView
	 */
	@RequestMapping("/delete/{id}")
	public ModelAndView delete(Integer id, WebRequest request);
}
