package com.pivotallabs.lunchtime.model;

import com.pivotallabs.lunchtime.repository.PersonRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class Matcher {
    private final PersonRepository personRepository;

    @Inject
    public Matcher(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Match getMatch() {
        List<Person> all = personRepository.findAll();
        if (all.size() == 0) return null;
        return new Match(all.get(0), all.get(1));
    }
}
