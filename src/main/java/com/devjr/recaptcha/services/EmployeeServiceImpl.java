package com.devjr.recaptcha.services;

import com.devjr.recaptcha.models.EmployeeEntity;
import com.devjr.recaptcha.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeEntity> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeEntity findById(Long id) {

        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);

        if(employeeEntity.isPresent()){
            return employeeEntity.get();
        }

        return employeeEntity.orElseThrow();
    }

    @Override
    public void createEmployeeEntity(EmployeeEntity employeeEntity) {

        employeeRepository.save(employeeEntity);

    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
