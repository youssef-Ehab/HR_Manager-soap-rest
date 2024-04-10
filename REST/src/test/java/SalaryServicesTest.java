import org.example.Presentation.DTOs.PayDayDTO;
import org.example.Services.SalaryServices;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SalaryServicesTest {
    SalaryServices salaryServices = new SalaryServices();

    @Test
    public void testUpdateSalary() {
        // Arrange
        String email = "youssef@gmail.com";
        BigDecimal amount = new BigDecimal(5000);

        // Act
        PayDayDTO result = salaryServices.updateSalary(email, amount);

        // Assert
        assertNotNull(result);
        assertEquals(amount, result.getSalary());
    }

    @Test
    public void testGiveBonus() {
        // Arrange
        String email = "youssef@gmail.com";
        BigDecimal amount = new BigDecimal(500);

        // Act
        String result = salaryServices.giveBonus(email, amount);

        // Assert
        assertEquals("done", result);
    }

    @Test
    public void testGivePenalty() {
        // Arrange
        String email = "youssef@gmail.com";
        BigDecimal amount = new BigDecimal(200);

        // Act
        String result = salaryServices.givePenalty(email, amount);

        // Assert
        assertEquals("done", result);
    }

    @Test
    public void testPayday() {
        // Act
        List<PayDayDTO> result = salaryServices.payday();

        // Assert
        assertNotNull(result);
        // Add more assertions based on your expected results
    }
}
