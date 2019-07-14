package com.spring.services;

import com.spring.domains.Person;

import java.util.List;

public interface PersonService {

    Person create(Person person);

    Person findById(Long id);

    List<Person> findAll();

    Person update(Person person);

    boolean deleteById(Long id);
}
