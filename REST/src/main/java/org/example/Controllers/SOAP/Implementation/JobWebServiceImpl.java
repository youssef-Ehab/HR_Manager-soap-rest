package org.example.Controllers.SOAP.Implementation;

import jakarta.jws.WebService;
import org.example.Controllers.SOAP.Interfaces.JobWebService;
import org.example.Presentation.DTOs.JobDto;
import org.example.Services.JobServices;

import java.util.List;

@WebService(endpointInterface = "org.example.Controllers.SOAP.Interfaces.JobWebService")
public class JobWebServiceImpl implements JobWebService {

    private final JobServices jobServices = new JobServices();

    @Override
    public List<JobDto> getAllJobs() {
        return jobServices.getAllJobs();
    }

    @Override
    public JobDto getJobByTitle(String title) {
        return jobServices.getJobByTitle(title);
    }

    @Override
    public boolean createJob(JobDto jobDto) {
        return jobServices.createJob(jobDto);
    }
}
