package org.example.Controllers;

import jakarta.ws.rs.*;
import org.example.Persistence.DAOS.Implementation.DepartmentDAO;
import org.example.Persistence.Database;
import org.example.Persistence.Entities.Department;
import org.example.Presentation.DTOs.DepartmentDto;
import org.example.Presentation.Mapper.DepartmentMapper;
import org.example.Services.DepartmentServices;

import java.util.List;
import java.util.Set;

@Path("/department")

public class DepartmentController {
    DepartmentServices departmentServices = new DepartmentServices();

    @GET
    @Path("/depNum/{depNum}")
    public Set<String> getAllEmployeesFromDepartment(@PathParam("depNum") int depNum) {
        return departmentServices.getAllEmployeesFromDepartment(depNum);
    }
    @GET
    @Path("/depName/{depName}")
    public Set<String> getAllEmployeesFromDepartment(@PathParam("depName") String depName) {
        return departmentServices.getAllEmployeesFromDepartment(depName);
    }

    @POST
    @Path("/create")
    @Consumes("application/json")
    public boolean createDepartment(List<String> data) {
        return departmentServices.createDepartment(data);
    }
}
