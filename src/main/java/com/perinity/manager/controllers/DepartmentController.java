package com.perinity.manager.controllers;

import com.perinity.manager.models.DTOs.DepartmentInfoDTO;
import com.perinity.manager.models.entities.Department;
import com.perinity.manager.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/departamentos")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping()
    public Long createDepartment(@RequestBody Department department){
        return departmentService.createDepartment(department);
    }

    @GetMapping()
    public List<DepartmentInfoDTO> listDepartmentInfo() {
        return departmentService.listDepartmentInfo();
    }
}
