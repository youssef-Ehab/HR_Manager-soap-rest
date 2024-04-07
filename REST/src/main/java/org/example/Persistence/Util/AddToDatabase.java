package org.example.Persistence.Util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.Persistence.Entities.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddToDatabase {
    public static void main(String[] args) {
//        // add data to the database
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hr");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // create a new employee
        Employee employee = new Employee();
        employee.setFirstName("New2");
        employee.setLastName("Employee2");
        employee.setEmail("new.employee@example.com");
        employee.setPhoneNumber("1234567890");
        employee.setHireDate(LocalDate.now());
        Job job = new Job();
        job.setJobTitle("New Job");
        job.setMinSalary(new BigDecimal("1000.00"));
        job.setMaxSalary(new BigDecimal("2000.00"));
        em.persist(job);
        employee.setJob(job); // replace with actual Job
        Salary salary = new Salary();
        salary.setBonus(BigDecimal.valueOf(0));
        salary.setSalaryAmount(new BigDecimal("1000.00"));
        salary.setPenalties(BigDecimal.valueOf(0));
        em.persist(salary);
        employee.setSalary(salary);
        Department department = new Department();
        department.setDepartmentName("New Department");
        em.persist(department);

        employee.setDepartment(department); // replace with actual Department
        Address address = new Address();
        address.setStreetAddress("123 Main St");
        address.setCity("Springfield");
        address.setCountry("USA");
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
