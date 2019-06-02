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
import vn.edu.vnuk.fashion.dao.ProductDao;
import vn.edu.vnuk.fashion.dao.ProductsColorDao;
import vn.edu.vnuk.fashion.model.Product;
import vn.edu.vnuk.fashion.model.ProductsColor;

@Controller
public class ProductsCollorsController {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ColorDao colorDao;	
	
	@Autowired
	private ProductsColorDao productsColorDao;

	@RequestMapping("/products-colors")
    public String index(
		
		@RequestParam(value="productId", required = false) String productId,
		@RequestParam(value="colorId", required = false) String colorId,
		Model model,
		ServletRequest request

	) throws SQLException{
		ProductsColor productsColor = new ProductsColor();
		
		if (productId != null) productsColor.setProductId(Long.parseLong(productId));
		
		if (colorId != null) productsColor.setColorId(Long.parseLong(colorId));
		
		model.addAttribute("productsColors", productsColorDao.read(productsColor));
        model.addAttribute("template", "product-color/index");
        return "_layout";
	}
    
    @RequestMapping("/products-colors/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("productsColor", productsColorDao.read(id));
        model.addAttribute("template", "product-color/show");
        return "_layout";
    }
    
    @RequestMapping("/products-colors/new")
    public String add(
    		
		ProductsColor productsColor,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "product-color/new");
    	model.addAttribute("products", productDao.read(new Product()));
    	model.addAttribute("colors", colorDao.read());
        return "_layout";
    }
    
    @RequestMapping("/products-colors/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		ProductsColor productsColor,
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
    	model.addAttribute("productsColor", productsColorDao.read(id));
    	model.addAttribute("products", productDao.read(new Product()));
    	model.addAttribute("colors", colorDao.read());
        model.addAttribute("template", "product-color/edit");

        return "_layout";
    }
    
    @RequestMapping(value="/products-colors", method=RequestMethod.POST)
    public String create(		
    	@Valid ProductsColor productsColor,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/products-colors/new";
        }
        
        productsColorDao.create(productsColor);
        return "redirect:/products-colors";
    }
    
    @RequestMapping(value="/products-colors/{id}", method=RequestMethod.PATCH)
    public String update(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		@Valid ProductsColor productsColor,
		BindingResult bindingResult,
		ServletRequest request,
		RedirectAttributes redirectAttributes
		
	) throws SQLException{
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/products-colors/%s/edit", id);
        }
        
        productsColorDao.update(productsColor);
        return backToShow ? String.format("redirect:/products-colors/%s", id) : "redirect:/products-colors";
    }
    
    //  delete with ajax
    @RequestMapping(value="/products-colors/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	productsColorDao.delete(id);
        response.setStatus(200);
    }
}
