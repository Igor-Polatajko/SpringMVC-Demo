package com.spring.daos;

import com.spring.domains.Person;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
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
    private Logger logger;

    @Autowired
    public PersonDaoImpl(SessionFactory sessionFactory, Logger logger) {
        this.sessionFactory = sessionFactory;
        this.logger = logger;
    }

    @Override
    public Person create(Person person) {

        if (logger.isEnabled(Level.INFO)) {
            logger.info("Creating person");
        }

        if (person == null) {
            if (logger.isEnabled(Level.WARN)) {
                logger.warn("Person was null");
            }
            return null;
        }

        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        person.setCreatedDate(currentLocalDateTime);
        person.setUpdatedDate(currentLocalDateTime);

        sessionFactory.getCurrentSession().save(person);

        if (logger.isEnabled(Level.INFO)) {
            logger.info("New person record was created (id = {})", person.getId());
        }

        return person;
    }

    @Override
    public Person findById(Long id) {

        if (logger.isEnabled(Level.INFO)) {
            logger.info("Finding person (id = {})", id);
        }

        return sessionFactory.getCurrentSession().get(Person.class, id);
    }

    @Override
    public Person update(Person person) {

        if (logger.isEnabled(Level.INFO)) {
            logger.info("Updating person");
        }

        if (person == null) {
            if (logger.isEnabled(Level.WARN)) {
                logger.warn("Person was null");
            }
            return null;
        }

        Person personFromDB = findById(person.getId());

        if (personFromDB == null) {
            if (logger.isEnabled(Level.WARN)) {
                logger.warn("Update exception. Person not found (id = {})", person.getId());
            }
            return null;
        }

        person.setUpdatedDate(LocalDateTime.now());
        person.setCreatedDate(personFromDB.getCreatedDate());

        sessionFactory.getCurrentSession().detach(personFromDB);
        sessionFactory.getCurrentSession().update(person);

        if (logger.isEnabled(Level.INFO)) {
            logger.info("Person record was updated (id = {})", person.getId());
        }

        return person;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> findAll() {

        if (logger.isEnabled(Level.INFO)) {
            logger.info("Finding all persons");
        }

        return sessionFactory.getCurrentSession().createQuery("FROM Person").list();
    }

    @Override
    public boolean deleteById(Long id) {

        if (logger.isEnabled(Level.INFO)) {
            logger.info("Deleting person (id = {})", id);
        }

        Person personToDelete = this.findById(id);

        if (personToDelete != null) {
            sessionFactory.getCurrentSession().delete(personToDelete);

            if (logger.isEnabled(Level.INFO)) {
                logger.info("Person record was deleted (id = {})", id);
            }

            return true;
        }
        else {
            if (logger.isEnabled(Level.WARN)) {
                logger.warn("Delete exception. Person not found (id = {})", id);
            }
            return false;
        }
    }
}
