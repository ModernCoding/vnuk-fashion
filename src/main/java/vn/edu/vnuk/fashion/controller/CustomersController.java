/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnuk.fashion.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.edu.vnuk.fashion.dao.CustomerDao;
import vn.edu.vnuk.fashion.dao.TitleDao;
import vn.edu.vnuk.fashion.model.Customer;

/**
 *
 * @author michel
 */
@Controller
public class CustomersController {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private TitleDao titleDao;
	

	@RequestMapping("/customers")
    public String index(
		
		@RequestParam(value="titleId", required = false) String titleId,
		Model model,
		ServletRequest request

	) throws SQLException{
        
		model.addAttribute("customers", customerDao.read(titleId));
		
		if (titleId != null) {
			model.addAttribute("title", titleDao.read(Long.parseLong(titleId)));
		}
		
        model.addAttribute("template", "customer/index");
        return "_layout";
   
	}
    
    
    @RequestMapping("/customers/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("customer", customerDao.read(id));
        model.addAttribute("template", "customer/show");
        return "_layout";
    }
    
    
    @RequestMapping("/customers/new")
    public String add(
    		
		Customer customer,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "customer/new");
    	model.addAttribute("titles", titleDao.read());
        return "_layout";
    }
    
    
    @RequestMapping("/customers/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Customer customer,
		Model model,
		ServletRequest request,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
		
	) throws SQLException{
    	
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	
    	model.addAttribute("backToShow", backToShow);
    	model.addAttribute("urlCompletion", backToShow ? String.format("/%s", id) : "");
    	model.addAttribute("customer", customerDao.read(id));
    	model.addAttribute("titles", titleDao.read());
        model.addAttribute("template", "customer/edit");

        return "_layout";
    
    }
    
    
    @RequestMapping(value="/customers", method=RequestMethod.POST)
    public String create(
		
    	@Valid Customer customer,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
    	
    	System.out.println(customer.toString());
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/customers/new";
        }
        
        
        customerDao.create(customer);
        return "redirect:/customers";
        
    }
    
    
    @RequestMapping(value="/customers/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid Customer customer,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/customers/%s/edit", id);
        }
        
        customerDao.update(customer);
        return backToShow ? String.format("redirect:/customers/%s", id) : "redirect:/customers";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/customers/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	customerDao.delete(id);
        response.setStatus(200);
    }
    
}
