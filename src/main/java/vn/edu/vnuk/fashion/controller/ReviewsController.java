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

import vn.edu.vnuk.fashion.dao.OrderDao;
import vn.edu.vnuk.fashion.dao.ReviewDao;
import vn.edu.vnuk.fashion.model.Order;
import vn.edu.vnuk.fashion.model.Review;

@Controller
public class ReviewsController {
	
	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@RequestMapping("/reviews")
    public String index(
		
		@RequestParam(value="orderId", required = false) String orderId,
		Model model,
		ServletRequest request

	) throws SQLException{
        Review review = new Review();
        
        if (orderId != null)
        	review.setOrderId(Long.valueOf(orderId));
        
		model.addAttribute("reviews", reviewDao.read(review));
		
        model.addAttribute("template", "review/index");
        return "_layout";
   
	}
    
    
    @RequestMapping("/reviews/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("review", reviewDao.read(id));
        model.addAttribute("template", "review/show");
        return "_layout";
    }
    
    
    @RequestMapping("/reviews/new")
    public String add(
    		
		Review review,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "review/new");
    	model.addAttribute("orders", orderDao.read(new Order()));
        return "_layout";
    }
    
    
    @RequestMapping("/reviews/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Review review,
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
    	model.addAttribute("review", reviewDao.read(id));
    	model.addAttribute("orders", orderDao.read(new Order()));
        model.addAttribute("template", "review/edit");

        return "_layout";
    
    }
    
    
    @RequestMapping(value="/reviews", method=RequestMethod.POST)
    public String create(
		
    	@Valid Review review,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/reviews/new";
        }
        
        reviewDao.create(review);
        return "redirect:/reviews";
        
    }
    
    
    @RequestMapping(value="/reviews/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid Review review,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/reviews/%s/edit", id);
        }
        
        reviewDao.update(review);
        return backToShow ? String.format("redirect:/reviews/%s", id) : "redirect:/reviews";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/reviews/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	reviewDao.delete(id);
        response.setStatus(200);
    }
    
}
