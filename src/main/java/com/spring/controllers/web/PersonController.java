package com.spring.controllers.web;

import com.spring.domains.Person;
import com.spring.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequestMapping("/")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @RequestMapping("")
    @ResponseStatus(HttpStatus.OK)
    public String index(Model model) {
        List<Person> people = personService.findAll();

        model.addAttribute("people", people);
        return "index";
    }

}
