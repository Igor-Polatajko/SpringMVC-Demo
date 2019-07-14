package com.spring.daos;

import com.spring.domains.Person;

import java.util.List;

public interface PersonDao {

    Person create(Person person);

    Person findById(Long id);

    Person update(Person person);

    List<Person> findAll();

    boolean deleteById(Long id);
}
