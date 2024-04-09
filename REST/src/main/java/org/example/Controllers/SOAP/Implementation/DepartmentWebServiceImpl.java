package org.example.Controllers.SOAP.Implementation;

import jakarta.jws.WebService;
import org.example.Controllers.SOAP.Interfaces.DepartmentWebService;
import org.example.Presentation.DTOs.DepartmentDto;
import org.example.Services.DepartmentServices;

import java.util.List;
import java.util.Set;

@WebService(endpointInterface = "org.example.Controllers.SOAP.Interfaces.DepartmentWebService")
public class DepartmentWebServiceImpl implements DepartmentWebService {

    private final DepartmentServices departmentServices = new DepartmentServices();

    @Override
    public Set<String> getAllEmployeesFromDepartmentByDepNum(int depNum) {
        return departmentServices.getAllEmployeesFromDepartment(depNum);
    }

    @Override
    public Set<String> getAllEmployeesFromDepartmentByDepName(String depName) {
        return departmentServices.getAllEmployeesFromDepartment(depName);
    }

    @Override
    public boolean createDepartment(List<String> data) {
        return departmentServices.createDepartment(data);
    }

    @Override
    public boolean setManager(String depName, String email) {
        return departmentServices.setManager(depName, email);
    }

    @Override
    public List<DepartmentDto> getDepartmentByManager(String email) {
        return departmentServices.getDepartmentByManager(email);
    }

    @Override
    public boolean removeManager(String depName) {
        return departmentServices.removeManager(depName);
    }
}
