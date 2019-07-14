package com.spring.services;

import com.spring.daos.PersonDao;
import com.spring.domains.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonDao personDao;

    @Autowired
    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public Person create(Person person) {
        return personDao.create(person);
    }

    @Override
    public Person findById(Long id) {
        return personDao.findById(id);
    }

    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public Person update(Person person) {
        return personDao.update(person);
    }

    @Override
    public boolean deleteById(Long id) {
        return personDao.deleteById(id);
    }
}
