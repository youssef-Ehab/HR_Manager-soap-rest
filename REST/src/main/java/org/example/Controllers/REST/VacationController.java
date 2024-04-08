package org.example.Controllers.REST;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.example.Presentation.DTOs.VacationDto;
import org.example.Services.VacationServices;

import java.util.List;

@Path("/vacation")
public class VacationController {
    VacationServices services = new VacationServices();
    @GET
    @Path("/all")
    public List<VacationDto> getAllVacations() {
        return services.getAllVacations();
    }
    @GET
    @Path("/email/{email}")
    public List<VacationDto> getVacationByEmail(@PathParam("email") String email) {
        return services.getVacationByEmail(email);
    }
    @GET
    @Path("/status/{status}")
    public List<VacationDto> getVacationByStatus(@PathParam("status") String status) {
        return services.getVacationByStatus(status);
    }
    @GET
    @Path("/accept/{email}")
    public boolean acceptVacation(@PathParam("email") String email) {
        return services.acceptVacation(email);
    }
    @GET
    @Path("/reject/{email}")
    public boolean rejectVacation(@PathParam("email") String email) {
        return services.rejectVacation(email);
    }
}
