import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DepartmentServicesTest {


    @Test
    public void testGetAllEmployeesFromDepartmentByNumber() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/department/depNum/12");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetAllEmployeesFromDepartmentByName() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/department/depName/Sales");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCreateDepartment() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/department/create");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);
        List<String> departmentData = Arrays.asList("Newn", "youssef@gmail.com");

        // Act
        Response response = request.post(Entity.entity(departmentData, MediaType.APPLICATION_JSON));

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
    @Test
    public void testSetManager() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/department/setManager/Sales/youssef@gmail.com");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetDepartmentByManager() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/department/manager/youssef@gmail.com");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
    @Test
    public void testRemoveManager() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/department/removeManager/Sales");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

}
