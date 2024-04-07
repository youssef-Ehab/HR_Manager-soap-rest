package org.example.Persistence.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "department_name")
    private String departmentName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees = new LinkedHashSet<>();

    @OneToMany(mappedBy = "department")
    private Set<Job> jobs = new LinkedHashSet<>();

    @Transient
    private Integer employeeCount;
    @Transient
    private Set<String> jobTitles;
    @Transient
    private Set<String> employeeNames;

    @PostLoad
    private void postLoad() {
        employeeCount = employees.size();
        jobTitles = new LinkedHashSet<>();
        employeeNames = new LinkedHashSet<>();
        for (Employee employee : employees) {
            employeeNames.add(employee.getFirstName() + " " + employee.getLastName());
        }
        for (Job job : jobs) {
            jobTitles.add(job.getJobTitle());
        }

            }
}