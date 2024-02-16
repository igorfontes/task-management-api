package com.perinity.manager.repositories;

import com.perinity.manager.models.entities.Department;
import com.perinity.manager.models.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    public List<Person> findByDepartment(Department department);
}
