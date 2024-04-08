package org.example.Presentation.DTOs;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link org.example.Persistence.Entities.PerformanceReview}
 */
@Data
public class PerformanceReviewDto implements Serializable {
    LocalDate reviewDate;
    String comments;
    Integer rating;
    String employeeName;
    String employeeEmail;
    String reviewerName;
    String reviewerEmail;
}