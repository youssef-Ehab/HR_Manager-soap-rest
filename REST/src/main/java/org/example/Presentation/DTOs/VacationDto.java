package org.example.Presentation.DTOs;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link org.example.Persistence.Entities.Vacation}
 */
@Data
public class VacationDto implements Serializable {
    LocalDate startDate;
    LocalDate endDate;
    @Size(max = 50)
    String status;
    String employeeName;
    String employeeEmail;
}