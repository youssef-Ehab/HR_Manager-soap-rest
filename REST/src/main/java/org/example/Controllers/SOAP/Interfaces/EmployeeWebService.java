package org.example.Controllers.SOAP.Interfaces;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.example.Presentation.DTOs.EmployeeDto;
import org.example.Presentation.DTOs.PayDayDTO;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

@WebService
public interface EmployeeWebService {

    @WebMethod(operationName = "getAllEmployees")
    List<EmployeeDto> getAllEmployees();

    @WebMethod(operationName = "getAllWorkingEmployees")
    List<EmployeeDto> getAllWorkingEmployees();

    @WebMethod(operationName = "getEmployeeByID")
    EmployeeDto getEmployeeByID(@WebParam(name = "id") int id);

    @WebMethod(operationName = "getEmployeeByEmail")
    EmployeeDto getEmployeeByEmail(@WebParam(name = "email") String email);

    @WebMethod(operationName = "getEmployeeByPhoneNumber")
    EmployeeDto getEmployeeByPhoneNumber(@WebParam(name = "phoneNumber") String phoneNumber);

    @WebMethod(operationName = "addEmployee")
    void addEmployee(@WebParam(name = "employeeDto") EmployeeDto employeeDto);

    @WebMethod(operationName = "removeEmployee")
    boolean removeEmployee(@WebParam(name = "email") String email);

    @WebMethod(operationName = "getAllRemovedEmployees")
    List<EmployeeDto> getAllRemovedEmployees();

    @WebMethod(operationName = "login")
    boolean login(@WebParam(name = "email") String email);

    @WebMethod(operationName = "logout")
    boolean logout(@WebParam(name = "email") String email);

    @WebMethod(operationName = "updateEmployee")
    EmployeeDto updateEmployee(@WebParam(name = "employeeDto") EmployeeDto employeeDto);
}
