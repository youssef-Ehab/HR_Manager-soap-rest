package org.example.Controllers.SOAP.Interfaces;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.example.Presentation.DTOs.DepartmentDto;

import java.util.List;
import java.util.Set;

@WebService
public interface DepartmentWebService {

    @WebMethod(operationName = "getAllEmployeesFromDepartmentByDepNum")
    Set<String> getAllEmployeesFromDepartmentByDepNum(@WebParam(name = "depNum") int depNum);

    @WebMethod(operationName = "getAllEmployeesFromDepartmentByDepName")
    Set<String> getAllEmployeesFromDepartmentByDepName(@WebParam(name = "depName") String depName);

    @WebMethod(operationName = "createDepartment")
    boolean createDepartment(@WebParam(name = "data") List<String> data);

    @WebMethod(operationName = "setManager")
    boolean setManager(@WebParam(name = "depName") String depName, @WebParam(name = "email") String email);

    @WebMethod(operationName = "getDepartmentByManager")
    List<DepartmentDto> getDepartmentByManager(@WebParam(name = "email") String email);

    @WebMethod(operationName = "removeManager")
    boolean removeManager(@WebParam(name = "depName") String depName);
}
