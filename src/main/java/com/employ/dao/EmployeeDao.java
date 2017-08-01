package com.employ.dao;

import com.employ.repository.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class EmployeeDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void addEmployee(Employee employee){
        entityManager.persist(employee);
    }

    public Employee getEmployeeById(int employeeID){
        return entityManager.find(Employee.class, employeeID);
    }
}
