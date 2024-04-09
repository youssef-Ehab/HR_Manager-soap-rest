package org.example.Controllers.SOAP.Implementation;

import jakarta.jws.WebService;
import org.example.Controllers.SOAP.Interfaces.EmployeeWebService;
import org.example.Presentation.DTOs.EmployeeDto;
import org.example.Presentation.DTOs.PayDayDTO;
import org.example.Services.EmployeeServices;

import java.util.List;

@WebService(endpointInterface = "org.example.Controllers.SOAP.Interfaces.EmployeeWebService")
public class EmployeeWebServiceImpl implements EmployeeWebService {

    private final EmployeeServices employeeServices = new EmployeeServices();

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeServices.getAllEmployees();
    }

    @Override
    public List<EmployeeDto> getAllWorkingEmployees() {
        return employeeServices.getAllWorkingEmployees();
    }

    @Override
    public EmployeeDto getEmployeeByID(int id) {
        return employeeServices.getEmployeeByID(id);
    }

    @Override
    public EmployeeDto getEmployeeByEmail(String email) {
        return employeeServices.getEmployeeByEmail(email);
    }

    @Override
    public EmployeeDto getEmployeeByPhoneNumber(String phoneNumber) {
        return employeeServices.getEmployeeByPhoneNumber(phoneNumber);
    }

    @Override
    public void addEmployee(EmployeeDto employeeDto) {
        employeeServices.addEmployee(employeeDto);
    }

    @Override
    public boolean removeEmployee(String email) {
        return employeeServices.removeEmployee(email);
    }

    @Override
    public List<EmployeeDto> getAllRemovedEmployees() {
        return employeeServices.getAllRemoved();
    }

    @Override
    public boolean login(String email) {
        return employeeServices.login(email);
    }

    @Override
    public boolean logout(String email) {
        return employeeServices.logout(email);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        return employeeServices.updateEmployee(employeeDto);
    }
}
