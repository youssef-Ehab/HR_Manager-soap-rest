package org.example.Presentation.DTOs;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link org.example.Persistence.Entities.Department}
 */
@Data
public class DepartmentDto implements Serializable {
    @Size(max = 255)
    String departmentName;
    Integer employeeCount;
    Set<String> jobTitles;
    Set<String> employeeNames;
}