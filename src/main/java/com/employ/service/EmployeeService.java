package com.employ.service;

import com.employ.dao.EmployeeDao;

import com.employ.repository.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public Employee getEmployeeById(int employeeId){
        Employee employee = employeeDao.getEmployeeById(employeeId);
        return employee;
    }

    public void addEmployee(Employee employee){
        employeeDao.addEmployee(employee);
    }
}
