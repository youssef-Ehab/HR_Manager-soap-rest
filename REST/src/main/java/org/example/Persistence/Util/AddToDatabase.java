package org.example.Persistence.Util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.Persistence.Entities.Address;
import org.example.Persistence.Entities.Department;
import org.example.Persistence.Entities.Employee;
import org.example.Persistence.Entities.Job;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddToDatabase {
    public static void main(String[] args) {
        // add data to the database
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hr");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // create a new employee
        Employee employee = new Employee();
        employee.setFirstName("New");
        employee.setLastName("Employee");
        employee.setEmail("new.employee@example.com");
        employee.setPhoneNumber("1234567890");
        employee.setHireDate(LocalDate.now());
        Job job = new Job();
        job.setJobTitle("New Job");
        job.setMinSalary(new BigDecimal("1000.00"));
        job.setMaxSalary(new BigDecimal("2000.00"));
        em.persist(job);
        employee.setJob(job); // replace with actual Job
        employee.setSalary(new BigDecimal("1000.00"));
        Department department = new Department();
        department.setDepartmentName("New Department");
        em.persist(department);

        employee.setDepartment(department); // replace with actual Department
        Address address = new Address();
        address.setStreetAddress("123 Main St");
        address.setCity("Springfield");
        address.setState("IL");
        address.setCountry("USA");
        address.setPostalCode("62701");
        em.persist(address);
        employee.setAddress(address); // replace with actual Address
        employee.setVacationDays(20);
        employee.setManager(null); // replace with actual Employee
        em.persist(employee);



        em.getTransaction().commit();
        em.close();
        emf.close();

    }
}
