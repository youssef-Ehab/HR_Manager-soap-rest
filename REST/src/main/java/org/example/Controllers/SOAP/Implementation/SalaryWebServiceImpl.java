package org.example.Controllers.SOAP.Implementation;

import jakarta.jws.WebService;
import org.example.Controllers.SOAP.Interfaces.SalaryWebService;
import org.example.Presentation.DTOs.PayDayDTO;
import org.example.Services.SalaryServices;

import java.math.BigDecimal;
import java.util.List;

@WebService(endpointInterface = "org.example.Controllers.SOAP.Interfaces.SalaryWebService")
public class SalaryWebServiceImpl implements SalaryWebService {

    private final SalaryServices salaryServices = new SalaryServices();

    @Override
    public PayDayDTO raiseSalary(String email, BigDecimal amount) {
        return salaryServices.updateSalary(email, amount);
    }

    @Override
    public String giveBonus(String email, BigDecimal amount) {
        return salaryServices.giveBonus(email, amount);
    }

    @Override
    public String givePenalty(String email, BigDecimal amount) {
        return salaryServices.givePenalty(email, amount);
    }

    @Override
    public List<PayDayDTO> payday() {
        return salaryServices.payday();
    }
}
