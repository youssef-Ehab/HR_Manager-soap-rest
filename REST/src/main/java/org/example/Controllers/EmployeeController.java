package org.example.Controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.Persistence.DAOS.Implementation.EmployeeDAO;
import org.example.Persistence.Database;
import org.example.Persistence.Entities.Employee;
import org.example.Presentation.DTOs.EmployeeDto;
import org.example.Presentation.DTOs.PayDayDTO;
import org.example.Presentation.Mapper.EmployeeMapper;
import org.example.Services.EmployeeServices;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

@Path("/employee")

public class EmployeeController {
    EmployeeServices employeeServices = new EmployeeServices();

    @GET
    @Path("/all")
    public List<EmployeeDto> getAllEmployees()

    {
        return employeeServices.getAllEmployees();
    }
    @GET
    @Path("/working")
    public List<EmployeeDto> getAllWorkingEmployees()
    {
        return employeeServices.getAllWorkingEmployees();
    }
    @GET
    @Path("/id/{id}")
    public EmployeeDto getEmployeeByID(@PathParam("id") int id)
    {
        return employeeServices.getEmployeeByID(id);
    }
    @GET
    @Path("/email/{email}")
    public EmployeeDto getEmployeeByEmail(@PathParam("email") String email)
    {
        return employeeServices.getEmployeeByEmail(email);
    }
    @GET
    @Path("/phone/{phoneNumber}")
    public EmployeeDto getEmployeeByPhoneNumber(@PathParam("phoneNumber") String phoneNumber)
    {
        return employeeServices.getEmployeeByPhoneNumber(phoneNumber);
    }
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addEmployee(EmployeeDto employeeDto)
    {
        employeeServices.addEmployee(employeeDto);
    }

    @GET
    @Path("/remove/{email}")
    public void removeEmployee(@PathParam("email") String email)
    {
        employeeServices.removeEmployee(email);
    }
    @GET
    @Path("/allRemoved")
    public List<EmployeeDto> getAllFiredEmployees()
    {
        return employeeServices.getAllRemoved();
    }
    @GET
    @Path("/login/{email}")
    public boolean login(@PathParam("email") String email)
    {
        return employeeServices.login(email);
    }
    @GET
    @Path("/logout/{email}")
    public boolean logout(@PathParam("email") String email)
    {
        return employeeServices.logout(email);
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public EmployeeDto updateEmployee(EmployeeDto employeeDto)
    {
        return employeeServices.updateEmployee(employeeDto);
    }
}