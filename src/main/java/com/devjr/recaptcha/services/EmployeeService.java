package com.devjr.recaptcha.services;

import com.devjr.recaptcha.models.EmployeeEntity;

import java.util.List;

public interface EmployeeService {

    List<EmployeeEntity> findAll();

    EmployeeEntity findById(Long id);

    void createEmployeeEntity(EmployeeEntity employeeEntity);

    void deleteEmployeeById(Long id);
}
