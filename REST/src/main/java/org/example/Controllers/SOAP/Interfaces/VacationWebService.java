package org.example.Controllers.SOAP.Interfaces;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.example.Presentation.DTOs.VacationDto;

import java.util.List;

@WebService
public interface VacationWebService {

    @WebMethod(operationName = "getAllVacations")
    List<VacationDto> getAllVacations();

    @WebMethod(operationName = "getVacationByEmail")
    List<VacationDto> getVacationByEmail(@WebParam(name = "email") String email);

    @WebMethod(operationName = "getVacationByStatus")
    List<VacationDto> getVacationByStatus(@WebParam(name = "status") String status);

    @WebMethod(operationName = "acceptVacation")
    boolean acceptVacation(@WebParam(name = "email") String email);

    @WebMethod(operationName = "rejectVacation")
    boolean rejectVacation(@WebParam(name = "email") String email);
}
