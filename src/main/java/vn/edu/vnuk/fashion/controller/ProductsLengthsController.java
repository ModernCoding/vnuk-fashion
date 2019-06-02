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

import vn.edu.vnuk.fashion.dao.LengthDao;
import vn.edu.vnuk.fashion.dao.ProductDao;
import vn.edu.vnuk.fashion.dao.ProductsLengthDao;
import vn.edu.vnuk.fashion.model.Product;
import vn.edu.vnuk.fashion.model.ProductsLength;

@Controller
public class ProductsLengthsController {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private LengthDao lengthDao;	
	
	@Autowired
	private ProductsLengthDao productsLengthDao;

	@RequestMapping("/products-lengths")
    public String index(
		
		@RequestParam(value="productId", required = false) String productId,
		@RequestParam(value="lengthId", required = false) String lengthId,
		Model model,
		ServletRequest request

	) throws SQLException{
		ProductsLength productsLength = new ProductsLength();
		
		if (productId != null) productsLength.setProductId(Long.parseLong(productId));
		
		if (lengthId != null) productsLength.setLengthId(Long.parseLong(lengthId));
		
		model.addAttribute("productsLengths", productsLengthDao.read(productsLength));
        model.addAttribute("template", "product-length/index");
        return "_layout";
	}
    
    @RequestMapping("/products-lengths/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("productsLength", productsLengthDao.read(id));
        model.addAttribute("template", "product-length/show");
        return "_layout";
    }
    
    @RequestMapping("/products-lengths/new")
    public String add(
    		
		ProductsLength productsLength,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "product-length/new");
    	model.addAttribute("products", productDao.read(new Product()));
    	model.addAttribute("lengths", lengthDao.read());
        return "_layout";
    }
    
    @RequestMapping("/products-lengths/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		ProductsLength productsLength,
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
    	model.addAttribute("productsLength", productsLengthDao.read(id));
    	model.addAttribute("products", productDao.read(new Product()));
    	model.addAttribute("lengths", lengthDao.read());
        model.addAttribute("template", "product-length/edit");

        return "_layout";
    }
    
    @RequestMapping(value="/products-lengths", method=RequestMethod.POST)
    public String create(		
    	@Valid ProductsLength productsLength,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/products-lengths/new";
        }
        
        productsLengthDao.create(productsLength);
        return "redirect:/products-lengths";
    }
    
    @RequestMapping(value="/products-lengths/{id}", method=RequestMethod.PATCH)
    public String update(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		@Valid ProductsLength productsLength,
		BindingResult bindingResult,
		ServletRequest request,
		RedirectAttributes redirectAttributes
		
	) throws SQLException{
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/products-lengths/%s/edit", id);
        }
        
        productsLengthDao.update(productsLength);
        return backToShow ? String.format("redirect:/products-lengths/%s", id) : "redirect:/products-lengths";
    }
    
    //  delete with ajax
    @RequestMapping(value="/products-lengths/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	productsLengthDao.delete(id);
        response.setStatus(200);
    }
}
