package org.example.Services;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.example.Persistence.DAOS.Implementation.*;
import org.example.Persistence.Database;
import org.example.Persistence.Entities.Address;
import org.example.Persistence.Entities.Attendance;
import org.example.Persistence.Entities.Employee;
import org.example.Presentation.DTOs.EmployeeDto;
import org.example.Presentation.DTOs.PayDayDTO;
import org.example.Presentation.Mapper.EmployeeMapper;
import org.example.Persistence.Entities.Salary;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeServices {

    public List<EmployeeDto> getAllEmployees()
    {
        return Database.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);

            List<Employee> employees = employeeDAO.getAll();
             return EmployeeMapper.instance.toEmployeeDtoList(employees);
        });
    }
    public List<EmployeeDto> getAllWorkingEmployees()
    {
        return Database.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);

            List<Employee> employees = employeeDAO.getAllWorking();
            return EmployeeMapper.instance.toEmployeeDtoList(employees);
        });
    }

    public EmployeeDto getEmployeeByID(int id)
    {
       EmployeeDto employeeDto= Database.doInTransaction(entityManager -> {
            Employee employee = entityManager.find(Employee.class, id);
            return EmployeeMapper.instance.toEmployeeDto(employee);
        });
       return employeeDto;
    }

    public EmployeeDto getEmployeeByEmail(String email){
        return Database.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);
            Employee employee = employeeDAO.getEmployeeByEmail(email);
            return EmployeeMapper.instance.toEmployeeDto(employee);
        });
    }
    public EmployeeDto getEmployeeByPhoneNumber(String phoneNumber){
        return Database.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);
            Employee employee = employeeDAO.getEmployeeByPhoneNumber(phoneNumber);
            return EmployeeMapper.instance.toEmployeeDto(employee);
        });
    }

    public void addEmployee(EmployeeDto employeeDto) {
        Database.doInTransaction(entityManager -> {
            Employee employee = EmployeeMapper.instance.toEmployee(employeeDto);
            //set department
            DepartmentDAO departmentDAO = new DepartmentDAO(entityManager);
            employee.setDepartment(departmentDAO.getDepartmentByName(employeeDto.getDepartmentName()));
            //set job
            JobDAO jobDAO = new JobDAO(entityManager);
            employee.setJob(jobDAO.getJobByTitle(employeeDto.getJobTitle()));
            //set salary
            Salary salary = new Salary();
            salary.setSalaryAmount(employeeDto.getSalaryAmount());
            salary.setBonus(BigDecimal.valueOf(0));
            salary.setPenalties(BigDecimal.valueOf(0));
            entityManager.persist(salary);
            employee.setSalary(salary);
            //set address

            Address address = new Address();
            address.setStreetAddress(employeeDto.getStreet());
            address.setCity(employeeDto.getCity());
            address.setCountry(employeeDto.getCountry());
            entityManager.persist(address);
            employee.setAddress(address);
            //set manager
            EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);
            if(employeeDto.getManagerName()!=null){
                employee.setManager(employeeDAO.getEmployeeByName(employeeDto.getManagerName()));
            }
            entityManager.persist(employee);
            return null;
        });
    }
    public void removeEmployee(String email){
        Database.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);
            Employee employee = employeeDAO.getEmployeeByEmail(email);
            employee.setRemoved(true);
            return null;
        });
    }

    public List<EmployeeDto> getAllRemoved() {
        return Database.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);
            List<Employee> employees = employeeDAO.getAllRemoved();
            return EmployeeMapper.instance.toEmployeeDtoList(employees);
        });
    }


    public boolean login(String email) {
return Database.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);
            Employee employee = employeeDAO.getEmployeeByEmail(email);
            if (employee == null) {
                return false;
            }
            Optional<Attendance> check = new AttendanceDAO(entityManager).getLastAttendance(employee);
            if (check.isPresent() && check.get().getDate().equals(LocalDate.now())) {
                return false;
            }
        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);
        attendance.setDate(LocalDate.now());
        attendance.setCheckInTime(LocalTime.now());
        entityManager.persist(attendance);
        return true;
        });
    }

    public boolean logout(String email) {
        return Database.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);
            Employee employee = employeeDAO.getEmployeeByEmail(email);
            if (employee == null) {
                return false;
            }
            Optional<Attendance> check = new AttendanceDAO(entityManager).getLastAttendance(employee);
            if (check.isEmpty() || (check.get().getDate().equals(LocalDate.now()) && check.get().getCheckOutTime() != null)){
                return false;
            }
            AttendanceDAO attendanceDao = new AttendanceDAO(entityManager);
            Optional<Attendance> attendance = attendanceDao.getLastAttendance(employee);
            attendance.get().setCheckOutTime(LocalTime.now());
            return true;
        });
    }
}
