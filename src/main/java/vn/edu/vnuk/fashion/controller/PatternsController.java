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

import vn.edu.vnuk.fashion.dao.PatternDao;
import vn.edu.vnuk.fashion.model.Pattern;

@Controller
public class PatternsController {
	
	@Autowired
	private PatternDao dao;

	@RequestMapping("/patterns")
    public String index(Model model, ServletRequest request) throws SQLException{
        model.addAttribute("patterns", dao.read());
        model.addAttribute("template", "pattern/index");
        return "_layout";
    }
    
    @RequestMapping("/patterns/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("pattern", dao.read(id));
        model.addAttribute("template", "pattern/show");
        return "_layout";
    }
    
    
    @RequestMapping("/patterns/new")
    public String add(Pattern pattern, Model model, @ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors){
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
        model.addAttribute("template", "pattern/new");
        return "_layout";
    }
    
    
    @RequestMapping("/patterns/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Pattern pattern,
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
    	model.addAttribute("pattern", dao.read(id));
        model.addAttribute("template", "pattern/edit");

        return "_layout";
    
        
    }
    
    
    @RequestMapping(value="/patterns", method=RequestMethod.POST)
    public String create(
		
    	@Valid Pattern pattern,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
        
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/patterns/new";
        }
        
        dao.create(pattern);
        return "redirect:/patterns";
    }
    
    
    @RequestMapping(value="/patterns/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid Pattern pattern,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/patterns/%s/edit", id);
        }
        
        dao.update(pattern);
        return backToShow ? String.format("redirect:/patterns/%s", id) : "redirect:/patterns";
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/patterns/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	dao.delete(id);
        response.setStatus(200);
    }
}
