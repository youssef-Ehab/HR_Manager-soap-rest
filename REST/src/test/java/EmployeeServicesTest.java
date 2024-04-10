import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.Presentation.DTOs.EmployeeDto;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmployeeServicesTest {

    @Test
    public void testGetAllEmployees() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/employee/all");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetAllWorkingEmployees() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/employee/working");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetEmployeeByID() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/employee/id/1");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetEmployeeByEmail() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/employee/email/youssef@gmail.com");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetEmployeeByPhoneNumber() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/employee/phone/1234567890");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testAddEmployee() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/employee/add");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("youssef");
        employeeDto.setLastName("mohamed");
        employeeDto.setEmail("asd@asd.com");
        employeeDto.setPhoneNumber("01111111111");
        employeeDto.setHireDate(java.time.LocalDate.now());
        employeeDto.setVacationDays(10);
        employeeDto.setDepartmentName("Sales");
        employeeDto.setSalaryAmount(java.math.BigDecimal.valueOf(10000));
        employeeDto.setJobTitle("Sales Manager");
        employeeDto.setStreet("street");
        employeeDto.setCity("city");
        employeeDto.setCountry("country");


        // Act
        Response response = request.post(Entity.entity(employeeDto, MediaType.APPLICATION_JSON));

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testRemoveEmployee() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/employee/remove/youssef@gmail.com");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetAllRemoved() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/employee/allRemoved");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testLogin() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/employee/login/youssef@gmail.com");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testLogout() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/employee/logout/youssef@gmail.com");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testUpdateEmployee() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/employee/update");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmail("youssef@gmail.com");
        employeeDto.setCity("giza");

        // Act
        Response response = request.post(Entity.entity(employeeDto, MediaType.APPLICATION_JSON));

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
