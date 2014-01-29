package com.pivotallabs.lunchtime.repository;

import com.pivotallabs.lunchtime.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
