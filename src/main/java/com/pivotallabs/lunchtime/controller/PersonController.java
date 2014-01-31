package com.pivotallabs.lunchtime.controller;


import com.pivotallabs.lunchtime.model.Person;
import com.pivotallabs.lunchtime.repository.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    private final PersonRepository personRepository;

    @Inject
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping("/{personId}")
    public String view(@PathVariable("personId") String personId, Model model) {
        model.addAttribute("person", personRepository.findOne(Long.valueOf(personId)));
        return "person/show";
    }

    @RequestMapping(method = GET)
    public String index(Person person, Model model) {
        model.addAttribute("people", personRepository.findAll());
        model.addAttribute("person", (person == null) ? new Person() : person);
        return "index";
    }

    @ResponseStatus(BAD_REQUEST)
    @RequestMapping(method = POST)
    public String create(@Valid Person person, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
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
