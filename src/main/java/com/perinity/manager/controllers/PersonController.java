package com.perinity.manager.controllers;

import com.perinity.manager.models.entities.Person;
import com.perinity.manager.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/post/pessoas")
    public Person createPerson(@RequestBody Person person){
        return personService.createPerson(person);
    }

    @PutMapping("/put/pessoas/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person person){
        return personService.updatePerson(id, person);
    }

    @DeleteMapping("/delete/pessoas/{id}")
    public void deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
    }

}
