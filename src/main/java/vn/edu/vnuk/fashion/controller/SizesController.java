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

import vn.edu.vnuk.fashion.dao.SizeDao;
import vn.edu.vnuk.fashion.model.Size;

@Controller
public class SizesController {
	
	@Autowired
	private SizeDao dao;

	@RequestMapping("/sizes")
    public String index(Model model, ServletRequest request) throws SQLException{
        model.addAttribute("sizes", dao.read());
        model.addAttribute("template", "size/index");
        return "_layout";
    }
    
    
    @RequestMapping("/sizes/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("size", dao.read(id));
        model.addAttribute("template", "size/show");
        return "_layout";
    }
    
    
    @RequestMapping("/sizes/new")
    public String add(Size size, Model model, @ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors){
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
        model.addAttribute("template", "size/new");
        return "_layout";
    }
    
    
    @RequestMapping("/sizes/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Size size,
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
    	model.addAttribute("size", dao.read(id));
        model.addAttribute("template", "size/edit");

        return "_layout";
    
        
    }
    
    
    @RequestMapping(value="/sizes", method=RequestMethod.POST)
    public String create(
		
    	@Valid Size size,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
        
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/sizes/new";
        }
        
        dao.create(size);
        return "redirect:/sizes";
        
        
    }
    
    
    @RequestMapping(value="/sizes/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid Size size,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/sizes/%s/edit", id);
        }
        
        dao.update(size);
        return backToShow ? String.format("redirect:/sizes/%s", id) : "redirect:/sizes";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/sizes/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	dao.delete(id);
        response.setStatus(200);
    }
    
}
