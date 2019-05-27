package vn.edu.vnuk.fashion.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

import vn.edu.vnuk.fashion.dao.ProductDao;
import vn.edu.vnuk.fashion.dao.ProductsSizeDao;
import vn.edu.vnuk.fashion.dao.SizeDao;
import vn.edu.vnuk.fashion.model.Product;
import vn.edu.vnuk.fashion.model.ProductsSize;

@Controller
public class ProductsSizesController {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private SizeDao sizeDao;	
	
	@Autowired
	private ProductsSizeDao productsSizeDao;

	@RequestMapping("/products-sizes")
    public String index(
		
		@RequestParam(value="productId", required = false) String productId,
		@RequestParam(value="sizeId", required = false) String sizeId,
		Model model,
		ServletRequest request

	) throws SQLException{
        
		model.addAttribute("productsSizes", productsSizeDao.read(productId, sizeId));
		
        model.addAttribute("template", "product-size/index");
        return "_layout";
   
	}
    
    
    @RequestMapping("/products-sizes/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("productSize", productsSizeDao.read(id));
        model.addAttribute("template", "product-size/show");
        return "_layout";
    }
    
    
    @RequestMapping("/products-sizes/new")
    public String add(
    		
		ProductsSize productsSize,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "product-size/new");
    	List<Product> products = productDao.read(new Product());
    	model.addAttribute("products", products);
    	model.addAttribute("sizes", sizeDao.read());
        return "_layout";
    }
    
    
    @RequestMapping("/products-sizes/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		ProductsSize productsSize,
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
    	model.addAttribute("productsSize", productsSizeDao.read(id));
    	model.addAttribute("products", productDao.read(new Product()));
    	model.addAttribute("sizes", sizeDao.read());
        model.addAttribute("template", "product-size/edit");

        return "_layout";
    
    }
    
    
    @RequestMapping(value="/products-sizes", method=RequestMethod.POST)
    public String create(		
    	@Valid ProductsSize productsSize,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/products-sizes/new";
        }
        
        productsSizeDao.create(productsSize);
        return "redirect:/products-sizes";
    }
    
    
    @RequestMapping(value="/products-sizes/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid ProductsSize productsSize,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/products-sizes/%s/edit", id);
        }
        
        productsSizeDao.update(productsSize);
        return backToShow ? String.format("redirect:/products-sizes/%s", id) : "redirect:/products-sizes";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/products-sizes/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	productsSizeDao.delete(id);
        response.setStatus(200);
    }
    
}
