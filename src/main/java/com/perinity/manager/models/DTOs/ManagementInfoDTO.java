package com.perinity.manager.models.DTOs;

import com.perinity.manager.models.entities.Department;

public record ManagementInfoDTO(String personName, Department department, Long spentHoursTasks) {
}
