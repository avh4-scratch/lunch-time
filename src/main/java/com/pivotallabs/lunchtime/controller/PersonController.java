package com.pivotallabs.lunchtime.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;
import org.thymeleaf.spring4.view.ThymeleafView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class PersonController {


    @RequestMapping(value = "/person", method = GET)
    public String index(Model model) {
        return "index";
    }

//    public String create() {
//
//    }
}
