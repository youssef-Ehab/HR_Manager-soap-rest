package org.example.Services;

import org.example.Persistence.DAOS.Implementation.EmployeeDAO;
import org.example.Persistence.Database;
import org.example.Persistence.Entities.Employee;
import org.example.Persistence.Entities.Salary;
import org.example.Presentation.DTOs.PayDayDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SalaryServices {
    public PayDayDTO updateSalary(String email, BigDecimal amount) {
        return Database.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);
            Employee employee = employeeDAO.getEmployeeByEmail(email);
            if (employee == null) {
                return null;
            }
            Salary salary = employee.getSalary();
            salary.setSalaryAmount(amount);
            PayDayDTO payDayDTO = new PayDayDTO();
            payDayDTO.setFirstName(employee.getFirstName());
            payDayDTO.setLastName(employee.getLastName());
            payDayDTO.setSalary(salary.getSalaryAmount());
            return payDayDTO;
        });
    }

    public String giveBonus(String email, BigDecimal amount) {
        return Database.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);
            Employee employee = employeeDAO.getEmployeeByEmail(email);
            if (employee == null) {
                return "error";
            }
            Salary salary = employee.getSalary();
            salary.setBonus(amount);
            return "done";
        });
    }

    public String givePenalty(String email, BigDecimal amount) {
        return Database.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);
            Employee employee = employeeDAO.getEmployeeByEmail(email);
            if (employee == null) {
                return "error";
            }
            Salary salary = employee.getSalary();
            salary.setPenalties(amount);
            return "done";
        });
    }

    public List<PayDayDTO> payday() {
        return Database.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);
            List<Employee> employees = employeeDAO.getAllWorking();
            List<PayDayDTO>payDayDTOS = new ArrayList<>();
            for (int i = 0; i <employees.size() ; i++) {
                PayDayDTO payDayDTO = new PayDayDTO();
                payDayDTO.setFirstName(employees.get(i).getFirstName());
                payDayDTO.setLastName(employees.get(i).getLastName());
                Salary salary = employees.get(i).getSalary();
                payDayDTO.setSalary(salary.getSalaryAmount().add(salary.getBonus()).subtract(salary.getPenalties()));
                payDayDTOS.add(payDayDTO);
                salary.setPenalties(BigDecimal.valueOf(0));
                salary.setBonus(BigDecimal.valueOf(0));
            }
            return payDayDTOS;
        });
    }
}
