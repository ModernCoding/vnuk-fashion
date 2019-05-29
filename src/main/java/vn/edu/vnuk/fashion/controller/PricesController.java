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

import vn.edu.vnuk.fashion.dao.PriceDao;
import vn.edu.vnuk.fashion.dao.PriceTypeDao;
import vn.edu.vnuk.fashion.dao.ProductsColorDao;
import vn.edu.vnuk.fashion.dao.ProductsLengthDao;
import vn.edu.vnuk.fashion.dao.ProductsPatternDao;
import vn.edu.vnuk.fashion.dao.ProductsSizeDao;
import vn.edu.vnuk.fashion.dao.SellerDao;
import vn.edu.vnuk.fashion.model.Price;
import vn.edu.vnuk.fashion.model.ProductsColor;
import vn.edu.vnuk.fashion.model.ProductsLength;

@Controller
public class PricesController {

	@Autowired
	private PriceDao priceDao;
	
	@Autowired
	private ProductsSizeDao productsSizeDao;
	
	@Autowired
	private ProductsColorDao productsColorDao;
	
	@Autowired
	private ProductsPatternDao productsPatternDao;
	
	@Autowired
	private ProductsLengthDao productsLengthDao;
	
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired
	private PriceTypeDao priceTypeDao;

	@RequestMapping("/prices")
    public String index(
		
		@RequestParam(value="productsSizeId", required = false) String productsSizeId,
		@RequestParam(value="productsColorId", required = false) String productsColorId,
		@RequestParam(value="productsPatternId", required = false) String productsPatternId,
		@RequestParam(value="productsLengthId", required = false) String productsLengthId,
		@RequestParam(value="sellerId", required = false) String sellerId,
		@RequestParam(value="priceTypeId", required = false) String priceTypeId,
		Model model,
		ServletRequest request

	) throws SQLException{
		
		Price price = new Price();
		
		if (productsSizeId != null) price.setProductsSizeId(Long.valueOf(productsSizeId));
		
		if (productsColorId != null) price.setProductsColorId(Long.valueOf(productsColorId));
		
		if (productsPatternId != null) price.setProductsPatternId(Long.valueOf(productsPatternId));
		
		if (productsLengthId != null) price.setProductsLengthId(Long.valueOf(productsLengthId));
		
		if (sellerId != null) price.setSellerId(Long.valueOf(sellerId));
		
		if (priceTypeId != null) price.setPriceTypeId(Long.valueOf(priceTypeId));;
		
		model.addAttribute("prices", priceDao.read(price));
        model.addAttribute("template", "price/index");
        
        return "_layout";   
	}
    
    
    @RequestMapping("/prices/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("price", priceDao.read(id));
        model.addAttribute("template", "price/show");
        return "_layout";
    }
    
    
    @RequestMapping("/prices/new")
    public String add(
    		
		Price price,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "price/new");
    	String temp = null;
    	model.addAttribute("productsSizes", productsSizeDao.read(temp, temp));
    	model.addAttribute("productsColors", productsColorDao.read(new ProductsColor()));
    	model.addAttribute("productsPatterns", productsPatternDao.read(temp, temp));
    	model.addAttribute("productsLengths", productsLengthDao.read(new ProductsLength()));
    	model.addAttribute("sellers", sellerDao.read());
    	model.addAttribute("priceTypes", priceTypeDao.read());

    	return "_layout";
    }
    
    
    @RequestMapping("/prices/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Price price,
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
    	model.addAttribute("price", priceDao.read(id));
    	String temp = null;
    	model.addAttribute("productsSizes", productsSizeDao.read(temp, temp));
    	model.addAttribute("productsColors", productsColorDao.read(new ProductsColor()));
    	model.addAttribute("productsPatterns", productsPatternDao.read(temp, temp));
    	model.addAttribute("productsLengths", productsLengthDao.read(new ProductsLength()));
    	model.addAttribute("sellers", sellerDao.read());
    	model.addAttribute("priceTypes", priceTypeDao.read());    	
        model.addAttribute("template", "price/edit");

        return "_layout";
    
    }
    
    
    @RequestMapping(value="/prices", method=RequestMethod.POST)
    public String create(		
    	@Valid Price price,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/prices/new";
        }
        
        priceDao.create(price);
        return "redirect:/prices";
    }
    
    
    @RequestMapping(value="/prices/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid Price price,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/prices/%s/edit", id);
        }
        
        priceDao.update(price);
        return backToShow ? String.format("redirect:/prices/%s", id) : "redirect:/prices";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/prices/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	priceDao.delete(id);
        response.setStatus(200);
    }
	
}
