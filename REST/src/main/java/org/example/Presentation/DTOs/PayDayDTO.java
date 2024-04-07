package org.example.Presentation.DTOs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PayDayDTO {
    String firstName;
    String lastName;
    BigDecimal salary;
}
