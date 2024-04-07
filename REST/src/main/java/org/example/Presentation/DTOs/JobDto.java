package org.example.Presentation.DTOs;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link org.example.Persistence.Entities.Job}
 */
@Data
public class JobDto implements Serializable {
    @Size(max = 255)
    String jobTitle;
    BigDecimal minSalary;
    BigDecimal maxSalary;
    String departmentName;
}