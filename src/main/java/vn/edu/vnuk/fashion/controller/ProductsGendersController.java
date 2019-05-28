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

import vn.edu.vnuk.fashion.dao.GenderDao;
import vn.edu.vnuk.fashion.dao.ProductDao;
import vn.edu.vnuk.fashion.dao.ProductsGenderDao;
import vn.edu.vnuk.fashion.model.Product;
import vn.edu.vnuk.fashion.model.ProductsGender;

@Controller
public class ProductsGendersController {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private GenderDao genderDao;	
	
	@Autowired
	private ProductsGenderDao productsGenderDao;

	@RequestMapping("/products-genders")
    public String index(
		
		@RequestParam(value="productId", required = false) String productId,
		@RequestParam(value="genderId", required = false) String genderId,
		Model model,
		ServletRequest request

	) throws SQLException{
		model.addAttribute("productsGenders", productsGenderDao.read(productId, genderId));
        model.addAttribute("template", "product-gender/index");
        return "_layout";
	}
    
    @RequestMapping("/products-genders/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("productsGender", productsGenderDao.read(id));
        model.addAttribute("template", "product-gender/show");
        return "_layout";
    }
    
    @RequestMapping("/products-genders/new")
    public String add(
    		
		ProductsGender productsGender,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "product-gender/new");
    	model.addAttribute("products", productDao.read(new Product()));
    	model.addAttribute("genders", genderDao.read());
        return "_layout";
    }
    
    @RequestMapping("/products-genders/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		ProductsGender productsGender,
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
    	model.addAttribute("productsGender", productsGenderDao.read(id));
    	model.addAttribute("products", productDao.read(new Product()));
    	model.addAttribute("genders", genderDao.read());
        model.addAttribute("template", "product-gender/edit");

        return "_layout";
    }
    
    @RequestMapping(value="/products-genders", method=RequestMethod.POST)
    public String create(		
    	@Valid ProductsGender productsGender,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/products-genders/new";
        }
        
        productsGenderDao.create(productsGender);
        return "redirect:/products-genders";
    }
    
    @RequestMapping(value="/products-genders/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid ProductsGender productsGender,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/products-genders/%s/edit", id);
        }
        
        productsGenderDao.update(productsGender);
        return backToShow ? String.format("redirect:/products-genders/%s", id) : "redirect:/products-genders";
    }
    
    //  delete with ajax
    @RequestMapping(value="/products-genders/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	productsGenderDao.delete(id);
        response.setStatus(200);
    }
    
}
