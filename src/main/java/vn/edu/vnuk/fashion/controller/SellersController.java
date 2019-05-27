
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

import vn.edu.vnuk.fashion.dao.SellerDao;
import vn.edu.vnuk.fashion.model.Seller;

@Controller
public class SellersController {

    @Autowired
    private SellerDao dao;

    @RequestMapping("/sellers")
    public String index(Model model, ServletRequest request) throws SQLException{
        model.addAttribute("sellers", dao.read());
        model.addAttribute("template", "seller/index");
        return "_layout";
    }

    @RequestMapping("/sellers/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("seller", dao.read(id));
        model.addAttribute("template", "seller/show");
        return "_layout";
    }

    @RequestMapping("/sellers/new")
    public String add(Seller seller, Model model, @ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors){

        for(FieldError fieldError : fieldErrors) {
            model.addAttribute(
                    String.format("%sFieldError", fieldError.getField()),
                    fieldError.getDefaultMessage()
            );
        }

        model.addAttribute("template", "seller/new");
        return "_layout";
    }

    @RequestMapping("/sellers/{id}/edit")
    public String edit(

            @RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
            @PathVariable("id") Long id,
            Seller seller,
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
        model.addAttribute("seller", dao.read(id));
        model.addAttribute("template", "seller/edit");

        return "_layout";

    }

    @RequestMapping(value="/sellers", method=RequestMethod.POST)
    public String create(

            @Valid Seller seller,
            BindingResult bindingResult,
            ServletRequest request,
            RedirectAttributes redirectAttributes

    ) throws SQLException {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/sellers/new";
        }

        dao.create(seller);
        return "redirect:/sellers";

    }

    @RequestMapping(value="/sellers/{id}", method=RequestMethod.PATCH)
    public String update(

            @RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
            @PathVariable("id") Long id,
            @Valid Seller seller,
            BindingResult bindingResult,
            ServletRequest request,
            RedirectAttributes redirectAttributes

    ) throws SQLException{

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/sellers/%s/edit", id);
        }

        dao.update(seller);
        return backToShow ? String.format("redirect:/sellers/%s", id) : "redirect:/sellers";

    }

    //  delete with ajax
    @RequestMapping(value="/sellers/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
        dao.delete(id);
        response.setStatus(200);
    }

}
