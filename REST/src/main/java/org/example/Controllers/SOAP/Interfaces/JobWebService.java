package org.example.Controllers.SOAP.Interfaces;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.example.Presentation.DTOs.JobDto;

import java.util.List;

@WebService
public interface JobWebService {

    @WebMethod(operationName = "getAllJobs")
    List<JobDto> getAllJobs();

    @WebMethod(operationName = "getJobByTitle")
    JobDto getJobByTitle(@WebParam(name = "title") String title);

    @WebMethod(operationName = "createJob")
    boolean createJob(@WebParam(name = "jobDto") JobDto jobDto);
}
