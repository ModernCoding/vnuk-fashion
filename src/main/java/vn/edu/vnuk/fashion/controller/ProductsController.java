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

import vn.edu.vnuk.fashion.dao.CollarDao;
import vn.edu.vnuk.fashion.dao.HeightDao;
import vn.edu.vnuk.fashion.dao.MakerDao;
import vn.edu.vnuk.fashion.dao.MaterialDao;
import vn.edu.vnuk.fashion.dao.ProductDao;
import vn.edu.vnuk.fashion.dao.ShapeDao;
import vn.edu.vnuk.fashion.dao.SleeveDao;
import vn.edu.vnuk.fashion.dao.SubcategoryDao;
import vn.edu.vnuk.fashion.model.Product;

@Controller
public class ProductsController {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private SubcategoryDao subcategoryDao;
	
	@Autowired
	private SleeveDao sleeveDao;
	
	@Autowired
	private ShapeDao shapeDao;
	
	@Autowired
	private CollarDao collarDao;
	
	@Autowired
	private HeightDao heightDao;
	
	@Autowired
	private MaterialDao materialDao;
	
	@Autowired
	private MakerDao makerDao;

	@RequestMapping("/products")
    public String index(
		
		@RequestParam(value="subcategoryId", required = false) String subcategoryId,
		@RequestParam(value="sleeveId", required = false) String sleeveId,
		@RequestParam(value="shapeId", required = false) String shapeId,
		@RequestParam(value="collarId", required = false) String collarId,
		@RequestParam(value="heightId", required = false) String heightId,
		@RequestParam(value="materialId", required = false) String materialId,
		@RequestParam(value="makerId", required = false) String makerId,
		Model model,
		ServletRequest request

	) throws SQLException{
		
		Product product = new Product();
		
		if (subcategoryId != null) product.setSubcategoryId(Long.valueOf(subcategoryId));
		
		if (sleeveId != null) product.setSleeveId(Long.valueOf(sleeveId));
		
		if (shapeId != null) product.setShapeId(Long.valueOf(shapeId));
		
		if (collarId != null) product.setCollarId(Long.valueOf(collarId));
		
		if (heightId != null) product.setHeightId(Long.valueOf(heightId));
		
		if (materialId != null) product.setMaterialId(Long.valueOf(materialId));
		
		if (makerId != null) product.setMakerId(Long.valueOf(makerId));
        
		model.addAttribute("products", productDao.read(product));
        model.addAttribute("template", "product/index");
        
        return "_layout";   
	}
    
    
    @RequestMapping("/products/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("product", productDao.read(id));
        model.addAttribute("template", "product/show");
        return "_layout";
    }
    
    
    @RequestMapping("/products/new")
    public String add(
    		
		Product product,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "product/new");
    	List<Product> products = productDao.read(new Product());
    	model.addAttribute("products", products);
    	String temp = null;
    	model.addAttribute("subcategories", subcategoryDao.read(temp));
    	model.addAttribute("sleeves", sleeveDao.read());
    	model.addAttribute("shapes", shapeDao.read());
    	model.addAttribute("collars", collarDao.read());
    	model.addAttribute("heights", heightDao.read());
    	model.addAttribute("materials", materialDao.read());
    	model.addAttribute("makers", makerDao.read());
        return "_layout";
    }
    
    
    @RequestMapping("/products/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Product product,
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
    	model.addAttribute("products", productDao.read(new Product()));
    	String temp = null;
    	model.addAttribute("subcategories", subcategoryDao.read(temp));
    	model.addAttribute("sleeves", sleeveDao.read());
    	model.addAttribute("shapes", shapeDao.read());
    	model.addAttribute("collars", collarDao.read());
    	model.addAttribute("heights", heightDao.read());
    	model.addAttribute("materials", materialDao.read());
    	model.addAttribute("makers", makerDao.read());
        model.addAttribute("template", "product/edit");

        return "_layout";
    
    }
    
    
    @RequestMapping(value="/products", method=RequestMethod.POST)
    public String create(		
    	@Valid Product product,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/products/new";
        }
        
        productDao.create(product);
        return "redirect:/products";
    }
    
    
    @RequestMapping(value="/products/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid Product product,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/products/%s/edit", id);
        }
        
        productDao.update(product);
        return backToShow ? String.format("redirect:/products/%s", id) : "redirect:/products";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/products/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	productDao.delete(id);
        response.setStatus(200);
    }
 
}
