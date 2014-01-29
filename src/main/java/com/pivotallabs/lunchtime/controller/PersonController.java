package com.pivotallabs.lunchtime.controller;


import com.pivotallabs.lunchtime.model.Person;
import com.pivotallabs.lunchtime.repository.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.inject.Inject;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/person")
public class PersonController {

    @Inject PersonRepository personRepository;

    @RequestMapping(method = GET)
    public String index(Model model) {
        return "index";
    }

    @ResponseStatus(CREATED)
    @RequestMapping(method = POST, params = "email")
    public String create(String email) throws MissingServletRequestParameterException {
        if (email == null || email.equals("")) {
            throw new MissingServletRequestParameterException("email", "string");
        }
        Person person = new Person(email);
        personRepository.save(person);
        return "index";
    }
}
