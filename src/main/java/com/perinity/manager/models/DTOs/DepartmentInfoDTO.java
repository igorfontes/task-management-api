package com.perinity.manager.models.DTOs;

import com.perinity.manager.models.entities.Department;

public record DepartmentInfoDTO(Department department, Long peopleAmount, Long tasksAmount) {
}
