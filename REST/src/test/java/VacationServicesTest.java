import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VacationServicesTest {

    @Test
    public void testGetAllVacations() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/vacation/all");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetVacationByEmail() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/vacation/email/youssef@gmail.com");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetVacationByStatus() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/vacation/status/Approved");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testAcceptVacation() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/vacation/accept/john.doe@example.com");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testRejectVacation() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/vacation/reject/jane.smith@example.com");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}