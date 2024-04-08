package org.example.Persistence.DAOS.Implementation;

import jakarta.persistence.EntityManager;
import org.example.Persistence.DAOS.Generic.dao;
import org.example.Persistence.Entities.Employee;

import java.util.List;

public class EmployeeDAO extends dao<Employee> {
    public EmployeeDAO(EntityManager entityManager) {
        super(entityManager);
        super.setType(Employee.class);
    }
    public Employee getEmployeeByEmail(String email) {
        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e WHERE e.email = :email", Employee.class)
                .setParameter("email", email)
                .getResultList();

        if (employees.isEmpty()) {
            return null;
        } else {
            return employees.get(0);
        }
    }
    public Employee getEmployeeByPhoneNumber(String phoneNumber) {
        return entityManager.createQuery("SELECT e FROM Employee e WHERE e.phoneNumber = :phoneNumber", Employee.class)
                .setParameter("phoneNumber", phoneNumber)
                .getSingleResult();
    }
    public Employee getEmployeeByName(String FullName) {
        String[] name = FullName.split(" ");
        String firstName = name[0];
        String lastName = name[1];
        return entityManager.createQuery("SELECT e FROM Employee e WHERE e.firstName = :firstName AND e.lastName = :lastName", Employee.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getSingleResult();
    }
    public List<Employee> getAllWorking() {
        return entityManager.createQuery("SELECT e FROM Employee e WHERE e.removed = false", Employee.class)
                .getResultList();
    }
    public List<Employee> getAllRemoved() {
        return entityManager.createQuery("SELECT e FROM Employee e WHERE e.removed = true", Employee.class)
                .getResultList();
    }
}
