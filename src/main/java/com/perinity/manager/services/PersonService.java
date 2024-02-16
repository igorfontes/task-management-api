package com.perinity.manager.services;

import com.perinity.manager.models.entities.Person;
import com.perinity.manager.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person createPerson(Person person){
        return personRepository.save(person);
    }
    public Person updatePerson(Long id, Person person) {

        return personRepository.findById(id).map(it -> {
            person.setId(it.getId());
            return personRepository.save(person);
        }).orElseThrow();

    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

}
