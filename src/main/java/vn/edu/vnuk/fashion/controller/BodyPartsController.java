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

import vn.edu.vnuk.fashion.dao.BodyPartDao;
import vn.edu.vnuk.fashion.model.BodyPart;

@Controller
public class BodyPartsController {
	
	@Autowired
	private BodyPartDao dao;

	@RequestMapping("/body-parts")
    public String index(Model model, ServletRequest request) throws SQLException{
        model.addAttribute("bodyParts", dao.read());
        model.addAttribute("template", "body-part/index");
        return "_layout";
    }
    
    @RequestMapping("/body-parts/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("bodyPart", dao.read(id));
        model.addAttribute("template", "body-part/show");
        return "_layout";
    }
    
    @RequestMapping("/body-parts/new")
    public String add(BodyPart bodyPart, Model model, @ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors){
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
        model.addAttribute("template", "body-part/new");
        return "_layout";
    }
    
    @RequestMapping("/body-parts/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		BodyPart bodyPart,
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
    	model.addAttribute("bodyPart", dao.read(id));
        model.addAttribute("template", "body-part/edit");

        return "_layout";
    }
    
    @RequestMapping(value="/body-parts", method=RequestMethod.POST)
    public String create(
		
    	@Valid BodyPart bodyPart,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/body-parts/new";
        }
        
        dao.create(bodyPart);
        return "redirect:/body-parts";
    }
    
    @RequestMapping(value="/body-parts/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid BodyPart bodyPart,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/body-parts/%s/edit", id);
        }
        
        dao.update(bodyPart);
        return backToShow ? String.format("redirect:/body-parts/%s", id) : "redirect:/body-parts";
    }
    
    //  delete with ajax
    @RequestMapping(value="/body-parts/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	dao.delete(id);
        response.setStatus(200);
    }
}
