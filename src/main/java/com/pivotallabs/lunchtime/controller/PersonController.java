package com.pivotallabs.lunchtime.controller;


import com.pivotallabs.lunchtime.model.Person;
import com.pivotallabs.lunchtime.repository.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.inject.Inject;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/person")
public class PersonController {

    @Inject PersonRepository personRepository;

    @RequestMapping("/{person}")
    public String view(@PathVariable Person person, Model model) {
        person = personRepository.findOne(person.getId());
        model.addAttribute("person", person);
        return "person/show";
    }

    @RequestMapping(method = GET)
    public String index(@ModelAttribute("person") Person person, Model model) {
        model.addAttribute("people", personRepository.findAll());
        model.addAttribute("person", (person == null) ? new Person() : person);
        return "index";
    }

    @ResponseStatus(BAD_REQUEST)
    @RequestMapping(method = POST)
    public String create(@Valid Person person, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return index(person, model);
        } else {
            personRepository.save(person);
            return "redirect:/person";
        }
    }

/*
    @ExceptionHandler(ConstraintViolationException.class)
            @ResponseStatus(BAD_REQUEST)
    public String constraintViolation(ConstraintViolationException e) {
//        model.addAttribute("violations", e.getConstraintViolations());
        return "create_error";
    }*/
}
