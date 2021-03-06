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

import vn.edu.vnuk.fashion.dao.CategoryDao;
import vn.edu.vnuk.fashion.dao.SubcategoryDao;
import vn.edu.vnuk.fashion.model.Subcategory;

/**
 *
 * @author michel
 */
@Controller
public class SubcategoriesController {
	
	private SubcategoryDao subcategoryDao;
	private CategoryDao categoryDao;

	@Autowired
	public void setSubcategoryDao(SubcategoryDao subcategoryDao) {
		this.subcategoryDao = subcategoryDao;
	}

	@Autowired
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	

	@RequestMapping("/subcategories")
    public String index(
		
		@RequestParam(value="categoryId", required = false) String categoryId,
		Model model,
		ServletRequest request

	) throws SQLException{
        
		model.addAttribute("subcategories", subcategoryDao.read(categoryId));
		
		if (categoryId != null) {
			model.addAttribute("category", categoryDao.read(Long.parseLong(categoryId)));
		}
		
        model.addAttribute("template", "subcategory/index");
        return "_layout";
   
	}
    
    
    @RequestMapping("/subcategories/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("subcategory", subcategoryDao.read(id));
        model.addAttribute("template", "subcategory/show");
        return "_layout";
    }
    
    
    @RequestMapping("/subcategories/new")
    public String add(
    		
		Subcategory subcategory,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "subcategory/new");
    	model.addAttribute("categories", categoryDao.read());
        return "_layout";
    }
    
    
    @RequestMapping("/subcategories/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Subcategory subcategory,
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
    	model.addAttribute("subcategory", subcategoryDao.read(id));
    	model.addAttribute("categories", categoryDao.read());
        model.addAttribute("template", "subcategory/edit");

        return "_layout";
    
    }
    
    
    @RequestMapping(value="/subcategories", method=RequestMethod.POST)
    public String create(
		
    	@Valid Subcategory subcategory,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/subcategories/new";
        }
        
        
        subcategoryDao.create(subcategory);
        return "redirect:/subcategories";
        
    }
    
    
    @RequestMapping(value="/subcategories/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid Subcategory subcategory,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/subcategories/%s/edit", id);
        }
        
        subcategoryDao.update(subcategory);
        return backToShow ? String.format("redirect:/subcategories/%s", id) : "redirect:/subcategories";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/subcategories/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	subcategoryDao.delete(id);
        response.setStatus(200);
    }
    
}
