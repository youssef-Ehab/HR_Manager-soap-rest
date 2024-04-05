package org.example.Persistence.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "salaries")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "salary_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal salaryAmount;

    @Column(name = "penalties", precision = 10, scale = 2)
    private BigDecimal penalties;

    @Column(name = "bonus", precision = 10, scale = 2)
    private BigDecimal bonus;

}