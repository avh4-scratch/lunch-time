package com.pivotallabs.lunchtime.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person {
    @Basic @Id @GeneratedValue Long id;
    @Basic String email;

    public Person() {
    }

    public Person(String email) {
        this.email = email;
    }
}
