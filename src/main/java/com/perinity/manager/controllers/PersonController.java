package com.perinity.manager.controllers;

import com.perinity.manager.models.DTOs.ManagementInfoDTO;
import com.perinity.manager.models.entities.Person;
import com.perinity.manager.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/pessoas")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping()
    public Person createPerson(@RequestBody Person person){
        return personService.createPerson(person);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person person){
        return personService.updatePerson(id, person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
    }

    @GetMapping()
    public List<ManagementInfoDTO> listPeopleInfo() {
        return personService.listPeopleInfo();
    }

    @GetMapping("/gastos")
    public Long filterPeopleByNameAndDates(
        @RequestParam String name,
        @RequestParam Date startDate,
        @RequestParam Date endDate
    ) {
        return personService.filterPeopleByNameAndDates(name, startDate, endDate);
    }

}
