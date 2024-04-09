package org.example.Controllers.SOAP.Implementation;

import jakarta.jws.WebService;
import org.example.Controllers.SOAP.Interfaces.VacationWebService;
import org.example.Presentation.DTOs.VacationDto;
import org.example.Services.VacationServices;

import java.util.List;

@WebService(endpointInterface = "org.example.Controllers.SOAP.Interfaces.VacationWebService")
public class VacationWebServiceImpl implements VacationWebService {

    private final VacationServices vacationServices = new VacationServices();

    @Override
    public List<VacationDto> getAllVacations() {
        return vacationServices.getAllVacations();
    }

    @Override
    public List<VacationDto> getVacationByEmail(String email) {
        return vacationServices.getVacationByEmail(email);
    }

    @Override
    public List<VacationDto> getVacationByStatus(String status) {
        return vacationServices.getVacationByStatus(status);
    }

    @Override
    public boolean acceptVacation(String email) {
        return vacationServices.acceptVacation(email);
    }

    @Override
    public boolean rejectVacation(String email) {
        return vacationServices.rejectVacation(email);
    }
}
