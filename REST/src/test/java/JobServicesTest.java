import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.Presentation.DTOs.JobDto;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class JobServicesTest {

    @Test
    public void testGetAllJobs() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/job/all");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetJobByTitle() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/job/get/Sales Manager");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCreateJob() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/job/create");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);
        JobDto jobDto = new JobDto();
        jobDto.setJobTitle("Test Job");
        jobDto.setMinSalary(BigDecimal.valueOf(1000));
        jobDto.setMaxSalary(BigDecimal.valueOf(2000));
        jobDto.setDepartmentName("Sales");

        // Act
        Response response = request.post(Entity.entity(jobDto, MediaType.APPLICATION_JSON));

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
