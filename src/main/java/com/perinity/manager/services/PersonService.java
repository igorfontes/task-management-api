package com.perinity.manager.services;

import com.perinity.manager.models.DTOs.ManagementInfoDTO;
import com.perinity.manager.models.entities.Person;
import com.perinity.manager.models.entities.Task;
import com.perinity.manager.repositories.PersonRepository;
import com.perinity.manager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private TaskRepository taskRepository;

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

    public List<ManagementInfoDTO> listPeopleInfo() {
        return personRepository.findAll().stream().map(person -> {
            var personName = person.getName();
            var department = person.getDepartment();
            var spentHoursTasks = taskRepository.findById(person.getId()).stream()
                    .map(Task::getDuration)
                    .reduce(0L, Long::sum);

            return new ManagementInfoDTO(personName, department, spentHoursTasks);
        }).toList();
    }

    public Long filterPeopleByNameAndDates(
        String name,
        Date startDate,
        Date endDate
    ) {
        Optional<Person> personOptional = personRepository.findByName(name);

        if (personOptional.isPresent()) {
            var durations = taskRepository.findByPersonAndDeadlineBetween(personOptional.get(), startDate, endDate)
                    .stream()
                    .map(Task::getDuration)
                    .toList();

            if (!durations.isEmpty()) {
                return durations.stream().mapToLong(Long::longValue).sum() / durations.size();
            }
        }

        return 0L;

    }

}
