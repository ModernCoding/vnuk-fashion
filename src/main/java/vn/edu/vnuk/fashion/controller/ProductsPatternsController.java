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
import vn.edu.vnuk.fashion.dao.ProductDao;
import vn.edu.vnuk.fashion.dao.ProductsPatternDao;
import vn.edu.vnuk.fashion.model.Product;
import vn.edu.vnuk.fashion.model.ProductsPattern;

@Controller
public class ProductsPatternsController {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private PatternDao patternDao;	
	
	@Autowired
	private ProductsPatternDao productsPatternDao;

	@RequestMapping("/products-patterns")
    public String index(
		
		@RequestParam(value="productId", required = false) String productId,
		@RequestParam(value="patternId", required = false) String patternId,
		Model model,
		ServletRequest request

	) throws SQLException{
		model.addAttribute("productsPatterns", productsPatternDao.read(productId, patternId));
        model.addAttribute("template", "product-pattern/index");
        return "_layout";
	}
    
    @RequestMapping("/products-patterns/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("productsPattern", productsPatternDao.read(id));
        model.addAttribute("template", "product-pattern/show");
        return "_layout";
    }
    
    @RequestMapping("/products-patterns/new")
    public String add(
    		
		ProductsPattern productsPattern,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "product-pattern/new");
    	model.addAttribute("products", productDao.read(new Product()));
    	model.addAttribute("patterns", patternDao.read());
        return "_layout";
    }
    
    @RequestMapping("/products-patterns/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		ProductsPattern productsPattern,
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
    	model.addAttribute("productsPattern", productsPatternDao.read(id));
    	model.addAttribute("products", productDao.read(new Product()));
    	model.addAttribute("patterns", patternDao.read());
        model.addAttribute("template", "product-pattern/edit");

        return "_layout";
    }
    
    @RequestMapping(value="/products-patterns", method=RequestMethod.POST)
    public String create(		
    	@Valid ProductsPattern productsPattern,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/products-patterns/new";
        }
        
        productsPatternDao.create(productsPattern);
        return "redirect:/products-patterns";
    }
    
    @RequestMapping(value="/products-patterns/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid ProductsPattern productsPattern,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/products-patterns/%s/edit", id);
        }
        
        productsPatternDao.update(productsPattern);
        return backToShow ? String.format("redirect:/products-patterns/%s", id) : "redirect:/products-patterns";
    }
    
    //  delete with ajax
    @RequestMapping(value="/products-patterns/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	productsPatternDao.delete(id);
        response.setStatus(200);
    }
}
