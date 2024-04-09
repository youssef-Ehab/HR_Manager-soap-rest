package org.example.Controllers.SOAP.Interfaces;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.example.Presentation.DTOs.PayDayDTO;

import java.math.BigDecimal;
import java.util.List;

@WebService
public interface SalaryWebService {

    @WebMethod(operationName = "raiseSalary")
    PayDayDTO raiseSalary(@WebParam(name = "email") String email, @WebParam(name = "amount") BigDecimal amount);

    @WebMethod(operationName = "giveBonus")
    String giveBonus(@WebParam(name = "email") String email, @WebParam(name = "amount") BigDecimal amount);

    @WebMethod(operationName = "givePenalty")
    String givePenalty(@WebParam(name = "email") String email, @WebParam(name = "amount") BigDecimal amount);

    @WebMethod(operationName = "payday")
    List<PayDayDTO> payday();
}
