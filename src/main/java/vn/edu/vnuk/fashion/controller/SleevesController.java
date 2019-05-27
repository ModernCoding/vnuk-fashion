
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
import vn.edu.vnuk.fashion.dao.SleeveDao;
import vn.edu.vnuk.fashion.model.Sleeve;

@Controller
public class SleevesController {

    @Autowired
    private SleeveDao dao;

    @RequestMapping("/sleeves")
    public String index(Model model, ServletRequest request) throws SQLException{
        model.addAttribute("sleeves", dao.read());
        model.addAttribute("template", "sleeve/index");
        return "_layout";
    }

    @RequestMapping("/sleeves/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("sleeve", dao.read(id));
        model.addAttribute("template", "sleeve/show");
        return "_layout";
    }

    @RequestMapping("/sleeves/new")
    public String add(Sleeve sleeve, Model model, @ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors){

        for(FieldError fieldError : fieldErrors) {
            model.addAttribute(
                    String.format("%sFieldError", fieldError.getField()),
                    fieldError.getDefaultMessage()
            );
        }

        model.addAttribute("template", "sleeve/new");
        return "_layout";
    }

    @RequestMapping("/sleeves/{id}/edit")
    public String edit(

            @RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
            @PathVariable("id") Long id,
            Sleeve sleeve,
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
        model.addAttribute("sleeve", dao.read(id));
        model.addAttribute("template", "sleeve/edit");

        return "_layout";

    }

    @RequestMapping(value="/sleeves", method=RequestMethod.POST)
    public String create(

            @Valid Sleeve sleeve,
            BindingResult bindingResult,
            ServletRequest request,
            RedirectAttributes redirectAttributes

    ) throws SQLException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/sleeves/new";
        }

        dao.create(sleeve);
        return "redirect:/sleeves";

    }

    @RequestMapping(value="/sleeves/{id}", method=RequestMethod.PATCH)
    public String update(

            @RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
            @PathVariable("id") Long id,
            @Valid Sleeve sleeve,
            BindingResult bindingResult,
            ServletRequest request,
            RedirectAttributes redirectAttributes

    ) throws SQLException{

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/sleeves/%s/edit", id);
        }

        dao.update(sleeve);
        return backToShow ? String.format("redirect:/sleeves/%s", id) : "redirect:/sleeves";

    }

    //  delete with ajax
    @RequestMapping(value="/sleeves/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
        dao.delete(id);
        response.setStatus(200);
    }

}