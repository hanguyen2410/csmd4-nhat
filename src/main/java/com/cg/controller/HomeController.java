package com.cg.controller;

import com.cg.model.Category;
import com.cg.model.Unit;
import com.cg.service.category.ICategoryService;
import com.cg.service.unit.IUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    ICategoryService categoryService;
    @Autowired
    IUnitService iUnitService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/list")
    public String showAdminPage(){
        return "index";
    }



    @GetMapping("/products")
    public ModelAndView showHomePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("temp");
        List<Category> categories = categoryService.findAll();
        List<Unit> units = iUnitService.findAll();
        modelAndView.addObject("units", units);
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView showCreateProductForm() {
        ModelAndView modelAndView = new ModelAndView();
        List<Category> categories = categoryService.findAll();
        List<Unit> units = iUnitService.findAll();
        modelAndView.addObject("units", units);
        modelAndView.addObject("categories", categories);
        modelAndView.setViewName("addproduct");
        return modelAndView;
    }
}
