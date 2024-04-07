package org.example.Persistence.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "min_salary", precision = 10, scale = 2)
    private BigDecimal minSalary;

    @Column(name = "max_salary", precision = 10, scale = 2)
    private BigDecimal maxSalary;

    @JoinColumn(name = "department_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Department department;

    @Transient
    String departmentName;
    @PostLoad
    private void postLoad() {
        departmentName = department.getDepartmentName();
    }
}