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

import vn.edu.vnuk.fashion.dao.ColorDao;
import vn.edu.vnuk.fashion.model.Color;


@Controller
public class ColorsController {
	
	private ColorDao dao;
	
	@Autowired
	public void setCategoryDao(ColorDao dao) {
		this.dao = dao;
	}
	

	@RequestMapping("/colors")
    public String index(Model model, ServletRequest request) throws SQLException{
        model.addAttribute("colors", dao.read());
        model.addAttribute("template", "color/index");
        return "_layout";
    }
    
    
    @RequestMapping("/colors/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("color", dao.read(id));
        model.addAttribute("template", "color/show");
        return "_layout";
    }
    
    
    @RequestMapping("/colors/new")
    public String add(Color color, Model model, @ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors){
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
        model.addAttribute("template", "color/new");
        return "_layout";
    }
    
    
    @RequestMapping("/colors/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Color color,
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
    	model.addAttribute("color", dao.read(id));
        model.addAttribute("template", "color/edit");

        return "_layout";
    
        
    }
    
    
    @RequestMapping(value="/colors", method=RequestMethod.POST)
    public String create(
		
    	@Valid Color color,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
        
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/colors/new";
        }
        
        dao.create(color);
        return "redirect:/colors";
        
        
    }
    
    
    @RequestMapping(value="/colors/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid Color color,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/colors/%s/edit", id);
        }
        
        dao.update(color);
        return backToShow ? String.format("redirect:/colors/%s", id) : "redirect:/colors";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/colors/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	dao.delete(id);
        response.setStatus(200);
    }
}
