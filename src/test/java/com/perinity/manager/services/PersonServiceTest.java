package com.perinity.manager.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.perinity.manager.models.DTOs.ManagementInfoDTO;
import com.perinity.manager.models.entities.Department;
import com.perinity.manager.models.entities.Person;
import com.perinity.manager.models.entities.Task;
import com.perinity.manager.repositories.PersonRepository;
import com.perinity.manager.repositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonServiceTests {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private PersonService personService;

    private Person createTestPerson1() {
        var person = new Person();
        person.setId(1L);
        person.setName("person1");

        var department = new Department();
        department.setId(1L);
        department.setTitle("dept1");

        person.setDepartment(department);

        return person;
    }

    private Person createTestPerson2() {
        var person = new Person();
        person.setId(2L);
        person.setName("person2");

        var department = new Department();
        department.setId(2L);
        department.setTitle("dept2");

        person.setDepartment(department);

        return person;
    }

    private Task createTask1() {
        var task = new Task();
        task.setPerson(createTestPerson1());
        task.setId(1L);
        task.setTitle("task1");

        var department = new Department();
        department.setId(1L);
        department.setTitle("dept1");
        task.setDepartment(department);

        task.setDescription("desc1");
        task.setDeadline(new Date(2024,5,30));
        task.setDuration(1L);

        return task;
    }

    private Task createTask2() {
        var task = new Task();
        task.setPerson(createTestPerson2());
        task.setId(2L);
        task.setTitle("task2");

        var department = new Department();
        department.setId(1L);
        department.setTitle("dept1");
        task.setDepartment(department);

        task.setDescription("desc2");
        task.setDeadline(new Date(2024,5,6));
        task.setDuration(1L);

        return task;
    }

    @Test
    void listPeopleInfo_mustReturnAll() {
        var person1 = createTestPerson1();
        var person2 = createTestPerson2();
        var task1 = createTask1();
        var task2 = createTask2();

        List<Person> people = Arrays.asList(
                person1,
                person2
        );
        when(personRepository.findAll()).thenReturn(people);

        when(taskRepository.findById(person1.getId())).thenReturn(Optional.of(task1));

        when(taskRepository.findById(person2.getId())).thenReturn(Optional.of(task2));

        List<ManagementInfoDTO> result = personService.listPeopleInfo();

        assertNotNull(result);
        assertEquals("person1", result.get(0).personName());
        assertEquals(1L, result.get(0).spentHoursTasks());
    }

    @Test
    void filterPeopleByNameAndDates_whenThereIsNoTaskBetweenTheDates_mustReturnEmpty() {
        var startDate = new Date(124,5,4);
        var endDate = new Date(124,5,10);
        var person = createTestPerson1();
        var task1 = createTask1();

        when(personRepository.findByName(person.getName())).thenReturn(Optional.of(person));

        when(taskRepository.findByPersonAndDeadlineBetween(person, startDate, endDate)).thenReturn(List.of(task1));

        Long result = personService.filterPeopleByNameAndDates(person.getName(), startDate, endDate);

        assertEquals(1L, result);
    }

    @Test
    void testFilterPeopleByNameAndDatesNoPersonFound() {
        var startDate = new Date(124,5,4);
        var endDate = new Date(124,5,10);
        String personName = "Nonexistent Person";
        when(personRepository.findByName(personName)).thenReturn(Optional.empty());

        Long result = personService.filterPeopleByNameAndDates(personName, startDate, endDate);

        assertEquals(0L, result);
    }
}

