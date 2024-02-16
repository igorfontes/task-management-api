package com.perinity.manager.controllers;

import com.perinity.manager.models.entities.Department;
import com.perinity.manager.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/post/departamento")
    public Long createDepartment(@RequestBody Department department){
        return departmentService.createDepartment(department);
    }
}
