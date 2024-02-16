package com.perinity.manager.repositories;

import com.perinity.manager.models.entities.Department;
import com.perinity.manager.models.entities.Person;
import com.perinity.manager.models.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByPersonAndDeadlineBetween(Person person, Date startDate, Date endDate);

    List<Task> findByOrderByDeadlineAsc();

    List<Task> findByDepartment(Department department);
}
