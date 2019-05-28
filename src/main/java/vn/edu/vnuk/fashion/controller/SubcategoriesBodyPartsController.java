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
import vn.edu.vnuk.fashion.dao.SubcategoriesBodyPartDao;
import vn.edu.vnuk.fashion.dao.SubcategoryDao;
import vn.edu.vnuk.fashion.model.SubcategoriesBodyPart;


@Controller
public class SubcategoriesBodyPartsController {
	
	@Autowired
	private SubcategoriesBodyPartDao subcategoriesBodyPartDao;
	
	@Autowired
	private SubcategoryDao subcategoryDao;	
	
	@Autowired
	private BodyPartDao bodyPartDao;

	@RequestMapping("/subcategories-body-parts")
    public String index(
		
		@RequestParam(value="subcategoryId", required = false) String subcategoryId,
		@RequestParam(value="bodyPartId", required = false) String bodyPartId,
		Model model,
		ServletRequest request

	) throws SQLException{
		model.addAttribute("subcategoriesBodyParts", subcategoriesBodyPartDao.read(subcategoryId, bodyPartId));
        model.addAttribute("template", "subcategory-body-part/index");
        return "_layout";
	}
    
    @RequestMapping("/subcategories-body-parts/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("subcategoriesBodyPart", subcategoriesBodyPartDao.read(id));
        model.addAttribute("template", "subcategory-body-part/show");
        return "_layout";
    }
    
    @RequestMapping("/subcategories-body-parts/new")
    public String add(
    		
		SubcategoriesBodyPart subcategoriesBodyPart,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "subcategory-body-part/new");
    	String temp = null;
    	model.addAttribute("subcategories", subcategoryDao.read(temp));
    	model.addAttribute("bodyParts", bodyPartDao.read());
        return "_layout";
    }
    
    @RequestMapping("/subcategories-body-parts/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		SubcategoriesBodyPart subcategoriesBodyPart,
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
    	model.addAttribute("subcategoriesBodyPart", subcategoriesBodyPartDao.read(id));
    	String temp = null;
    	model.addAttribute("subcategories", subcategoryDao.read(temp));
    	model.addAttribute("bodyParts", bodyPartDao.read());
        model.addAttribute("template", "subcategory-body-part/edit");

        return "_layout";
    }
    
    @RequestMapping(value="/subcategories-body-parts", method=RequestMethod.POST)
    public String create(		
    	@Valid SubcategoriesBodyPart subcategoriesBodyPart,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/subcategories-body-parts/new";
        }
        
        subcategoriesBodyPartDao.create(subcategoriesBodyPart);
        return "redirect:/subcategories-body-parts";
    }
    
    @RequestMapping(value="/subcategories-body-parts/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid SubcategoriesBodyPart subcategoriesBodyPart,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/subcategories-body-parts/%s/edit", id);
        }
        
    	subcategoriesBodyPartDao.update(subcategoriesBodyPart);
        return backToShow ? String.format("redirect:/subcategories-body-parts/%s", id) : "redirect:/subcategories-body-parts";
    }
    
    //  delete with ajax
    @RequestMapping(value="/subcategories-body-parts/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	subcategoriesBodyPartDao.delete(id);
        response.setStatus(200);
    }
    
}
