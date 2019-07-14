package com.spring.daos;

import com.spring.domains.Person;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class PersonDaoImpl implements PersonDao {

    private SessionFactory sessionFactory;

    @Autowired
    public PersonDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Person create(Person person) {
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        person.setCreatedDate(currentLocalDateTime);
        person.setUpdatedDate(currentLocalDateTime);

        sessionFactory.getCurrentSession().save(person);
        return person;
    }

    @Override
    public Person findById(Long id) {
        return sessionFactory.getCurrentSession().get(Person.class, id);
    }

    @Override
    public Person update(Person person) {
        Person personFromDB = findById(person.getId());

        if (personFromDB == null) {
            return null;
        }

        person.setUpdatedDate(LocalDateTime.now());
        person.setCreatedDate(personFromDB.getCreatedDate());

        sessionFactory.getCurrentSession().detach(personFromDB);
        sessionFactory.getCurrentSession().update(person);
        return person;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM Person").list();
    }

    @Override
    public boolean deleteById(Long id) {
        Person personToDelete = this.findById(id);

        if (personToDelete != null) {
            sessionFactory.getCurrentSession().delete(personToDelete);
            return true;
        }
        else {
            return false;
        }
    }
}
