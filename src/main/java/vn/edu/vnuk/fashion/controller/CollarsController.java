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

import vn.edu.vnuk.fashion.dao.CollarDao;
import vn.edu.vnuk.fashion.model.Collar;

@Controller
public class CollarsController {
	
	private CollarDao dao;
	
	@Autowired
	public void setCategoryDao(CollarDao dao) {
		this.dao = dao;
	}
	

	@RequestMapping("/collars")
    public String index(Model model, ServletRequest request) throws SQLException{
        model.addAttribute("collars", dao.read());
        model.addAttribute("template", "collar/index");
        return "_layout";
    }
    
    
    @RequestMapping("/collars/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("collar", dao.read(id));
        model.addAttribute("template", "collar/show");
        return "_layout";
    }
    
    
    @RequestMapping("/collars/new")
    public String add(Collar collar, Model model, @ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors){
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
        model.addAttribute("template", "collar/new");
        return "_layout";
    }
    
    
    @RequestMapping("/collars/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Collar collar,
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
    	model.addAttribute("collar", dao.read(id));
        model.addAttribute("template", "collar/edit");

        return "_layout";
    
        
    }
    
    
    @RequestMapping(value="/collars", method=RequestMethod.POST)
    public String create(
		
    	@Valid Collar collar,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
        
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/collars/new";
        }
        
        dao.create(collar);
        return "redirect:/collars";
        
        
    }
    
    
    @RequestMapping(value="/collars/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid Collar collar,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/collars/%s/edit", id);
        }
        
        dao.update(collar);
        return backToShow ? String.format("redirect:/collars/%s", id) : "redirect:/collars";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/collars/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	dao.delete(id);
        response.setStatus(200);
    }
}
