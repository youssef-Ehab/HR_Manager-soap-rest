package org.example.Controllers.REST;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.example.Presentation.DTOs.JobDto;
import org.example.Services.JobServices;

import java.math.BigDecimal;
import java.util.List;

@Path("/job")
public class JobController {
    JobServices jobServices = new JobServices();

    @GET
    @Path("/all")
    public List<JobDto> getAllJobs()
    {
        return jobServices.getAllJobs();
    }
    @GET
    @Path("/get/{title}")
    public JobDto getJobById(@PathParam("title") String title)
    {
        return jobServices.getJobByTitle(title);
    }
    @POST
    @Path("/create")
    public boolean createJob(JobDto jobDto)
    {
        return jobServices.createJob(jobDto);
    }

}
