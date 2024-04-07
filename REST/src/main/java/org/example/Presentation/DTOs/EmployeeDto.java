package org.example.Presentation.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;
import org.example.Persistence.Entities.Employee;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link Employee}
 */
@Data
public class EmployeeDto implements Serializable {
    @NotNull
    @Size(max = 255)
    String firstName;
    @NotNull
    @Size(max = 255)
    String lastName;
    @NotNull
    @Size(max = 255)
    String email;
    @NotNull
    @Size(max = 20)
    String phoneNumber;
    @NotNull
    LocalDate hireDate;
    @NotNull
    Integer vacationDays;
    Boolean removed;


    String managerName;
    @NotNull
    String departmentName;
    BigDecimal salaryAmount;
    String jobTitle;
    String street;
    String city;
    String country;

}