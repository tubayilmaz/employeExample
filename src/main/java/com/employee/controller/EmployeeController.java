package com.employee.controller;

import com.employee.repository.EmployeeRepository;
import com.employee.repository.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id) {
        Employee employee = employeeRepository.findOne(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("employees")
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> list = employeeRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("employee")
    public ResponseEntity<Void> addEmployee(@RequestBody Employee employee, UriComponentsBuilder builder) {
        employeeRepository.save(employee);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/employee/{id}").buildAndExpand(employee.getEmployeeId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("employee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        Employee tmpEmployee= new Employee();
        tmpEmployee.setEmployeeId(employee.getEmployeeId());
        tmpEmployee.setLastName(employee.getLastName());
        tmpEmployee.setFirstName(employee.getFirstName());
        return new ResponseEntity<>(tmpEmployee, HttpStatus.OK);
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Integer id){
       if(!employeeRepository.exists(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       employeeRepository.delete(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("employee/count")
    public ResponseEntity<Long> employeeCounter(){
        return new ResponseEntity<>(employeeRepository.count(), HttpStatus.OK);
    }
}
