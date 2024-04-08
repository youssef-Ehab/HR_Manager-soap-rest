package org.example.Controllers.REST;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.example.Presentation.DTOs.PayDayDTO;
import org.example.Services.SalaryServices;

import java.math.BigDecimal;
import java.util.List;

@Path("/salary")
public class SalaryController {
    SalaryServices salaryServices = new SalaryServices();

    @GET
    @Path("/updateSalary/{email}/{amount}")
    public PayDayDTO raiseSalary(@PathParam("email") String email, @PathParam("amount") BigDecimal amount)
    {
        return salaryServices.updateSalary(email, amount);
    }
    @GET
    @Path("/bonus/{email}/{amount}")
    public String giveBonus(@PathParam("email") String email, @PathParam("amount") BigDecimal amount)
    {
        return salaryServices.giveBonus(email, amount);
    }
    @GET
    @Path("/penalty/{email}/{amount}")
    public String givePenalty(@PathParam("email") String email, @PathParam("amount") BigDecimal amount)
    {
        return salaryServices.givePenalty(email, amount);
    }
    @GET
    @Path("/payday")
    public List<PayDayDTO> payday(){
        return salaryServices.payday();
    }
}
