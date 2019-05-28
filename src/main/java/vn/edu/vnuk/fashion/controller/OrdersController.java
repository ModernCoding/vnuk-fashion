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

import vn.edu.vnuk.fashion.dao.CustomerDao;
import vn.edu.vnuk.fashion.dao.OrderDao;
import vn.edu.vnuk.fashion.dao.PriceDao;
import vn.edu.vnuk.fashion.model.Customer;
import vn.edu.vnuk.fashion.model.Order;
import vn.edu.vnuk.fashion.model.Price;

@Controller
public class OrdersController {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private PriceDao priceDao;	
	
	@Autowired
	private OrderDao orderDao;

	@RequestMapping("/orders")
    public String index(
		
		@RequestParam(value="customerId", required = false) String customerId,
		@RequestParam(value="priceId", required = false) String priceId,
		Model model,
		ServletRequest request

	) throws SQLException{
		
		Order order = new Order();
		if (customerId != null)
			order.setCustomerId(Long.valueOf(customerId));
		
		if (priceId != null)
			order.setPriceId(Long.valueOf(priceId));
		
		model.addAttribute("orders", orderDao.read(order));
        model.addAttribute("template", "order/index");
        return "_layout";
	}
    
    @RequestMapping("/orders/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("order", orderDao.read(id));
        model.addAttribute("template", "order/show");
        return "_layout";
    }
    
    @RequestMapping("/orders/new")
    public String add(
    		
		Order order,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "order/new");
    	model.addAttribute("customers", customerDao.read(new Customer()));
    	model.addAttribute("prices", priceDao.read(new Price()));
        return "_layout";
    }
    
    @RequestMapping("/orders/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Order order,
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
    	model.addAttribute("order", orderDao.read(id));
    	model.addAttribute("customers", customerDao.read(new Customer()));
    	model.addAttribute("prices", priceDao.read(new Price()));
        model.addAttribute("template", "order/edit");

        return "_layout";
    }
    
    @RequestMapping(value="/orders", method=RequestMethod.POST)
    public String create(		
    	@Valid Order order,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/orders/new";
        }
        
        orderDao.create(order);
        return "redirect:/orders";
    }
    
    @RequestMapping(value="/orders/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid Order order,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/orders/%s/edit", id);
        }
        
        orderDao.update(order);
        return backToShow ? String.format("redirect:/orders/%s", id) : "redirect:/orders";
    }
    
    //  delete with ajax
    @RequestMapping(value="/orders/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	orderDao.delete(id);
        response.setStatus(200);
    }
    
}
