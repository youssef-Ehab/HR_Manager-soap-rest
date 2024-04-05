package org.example.Persistence.DAOS.Implementation;

import jakarta.persistence.EntityManager;
import org.example.Persistence.DAOS.Generic.dao;
import org.example.Persistence.Entities.Employee;

public class EmployeeDAO extends dao<Employee> {
    public EmployeeDAO(EntityManager entityManager) {
        super(entityManager);
        super.setType(Employee.class);
    }

}
