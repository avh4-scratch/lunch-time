package com.pivotallabs.lunchtime.controller;


import com.pivotallabs.lunchtime.model.Person;
import com.pivotallabs.lunchtime.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    private final PersonRepository personRepository;

    @Inject
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping("/{personId}")
    public Person view(@PathVariable("personId") Long personId) {
        return personRepository.findOne(personId);
    }

    @RequestMapping(method = GET)
    public List<Person> index(Person person) {
        return personRepository.findAll();
    }

    @ResponseStatus(CREATED)
    @RequestMapping(method = POST)
    public Person create(@RequestBody @Valid Person person) {
        return personRepository.save(person);
    }
}
