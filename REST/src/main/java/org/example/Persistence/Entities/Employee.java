package org.example.Persistence.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(max = 255)
    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false , unique = true)
    private String email;

    @Size(max = 20)
    @NotNull
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @NotNull
    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @NotNull
    @OneToOne
    @JoinColumn(name = "salary",nullable = false)
    private Salary salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @NotNull
    @Column(name = "vacation_days", nullable = false)
    private Integer vacationDays;

    @NotNull
    @Column(name = "removed", nullable = false)
    private Boolean removed = false;

    @OneToMany(mappedBy = "employee")
    private Set<Attendance> attendances = new LinkedHashSet<>();

    @OneToMany(mappedBy = "manager")
    private Set<Department> departments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "manager")
    private Set<Employee> employees = new LinkedHashSet<>();

    @OneToMany(mappedBy = "reviewer")
    private Set<PerformanceReview> performanceReviews = new LinkedHashSet<>();

    @OneToMany(mappedBy = "employee")
    private Set<Vacation> vacations = new LinkedHashSet<>();

    @Transient
    private String managerName;
    @Transient
    private String departmentName;
    @Transient
    private String jobTitle;
    @Transient
    private BigDecimal salaryAmount;
    @Transient
    String city;
    @Transient
    String country;
    @Transient
    String street;
    @Transient
    String managerEmail;
    @PostLoad
    private void postLoad() {
        if (manager != null) {
            managerName = manager.getFirstName() + " " + manager.getLastName();
            managerEmail = manager.getEmail();
        }
        if (manager  == null){
            managerName = "No Manager";
        }
        if (department != null) {
            departmentName = department.getDepartmentName();
        }
        if (job != null) {
            jobTitle = job.getJobTitle();
        }
        if (salary != null) {
            salaryAmount = salary.getSalaryAmount();
        }
        if (address != null) {
            city = address.getCity();
            country = address.getCountry();
            street = address.getStreetAddress();
        }
    }
}