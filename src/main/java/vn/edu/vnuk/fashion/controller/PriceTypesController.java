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

import vn.edu.vnuk.fashion.dao.PriceTypeDao;
import vn.edu.vnuk.fashion.model.PriceType;

@Controller
public class PriceTypesController {

    @Autowired
    private PriceTypeDao dao;

    @RequestMapping("/price-types")
    public String index(Model model, ServletRequest request) throws SQLException{
        model.addAttribute("priceTypes", dao.read());
        model.addAttribute("template", "price-type/index");
        return "_layout";
    }


    @RequestMapping("/price-types/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("priceType", dao.read(id));
        model.addAttribute("template", "price-type/show");
        return "_layout";
    }


    @RequestMapping("/price-types/new")
    public String add(PriceType priceType, Model model, @ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors){

        for(FieldError fieldError : fieldErrors) {
            model.addAttribute(
                    String.format("%sFieldError", fieldError.getField()),
                    fieldError.getDefaultMessage()
            );
        }

        model.addAttribute("template", "price-type/new");
        return "_layout";
    }


    @RequestMapping("/price-types/{id}/edit")
    public String edit(

            @RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
            @PathVariable("id") Long id,
            PriceType priceType,
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
        model.addAttribute("priceType", dao.read(id));
        model.addAttribute("template", "price-type/edit");

        return "_layout";
    }

    @RequestMapping(value="/price-types", method=RequestMethod.POST)
    public String create(

            @Valid PriceType priceType,
            BindingResult bindingResult,
            ServletRequest request,
            RedirectAttributes redirectAttributes

    ) throws SQLException{

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/price-type/new";
        }

        dao.create(priceType);
        return "redirect:/price-types";
    }

    @RequestMapping(value="/price-types/{id}", method=RequestMethod.PATCH)
    public String update(

            @RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
            @PathVariable("id") Long id,
            @Valid PriceType priceType,
            BindingResult bindingResult,
            ServletRequest request,
            RedirectAttributes redirectAttributes

    ) throws SQLException{
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/price-types/%s/edit", id);
        }

        dao.update(priceType);
        return backToShow ? String.format("redirect:/price-types/%s", id) : "redirect:/price-types";
    }

    //  delete with ajax
    @RequestMapping(value="/price-types/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
        dao.delete(id);
        response.setStatus(200);
    }
}
