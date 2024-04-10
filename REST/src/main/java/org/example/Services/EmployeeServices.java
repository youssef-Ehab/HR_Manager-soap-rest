package org.example.Services;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.example.Persistence.DAOS.Implementation.*;
import org.example.Persistence.Database;
import org.example.Persistence.Entities.*;
import org.example.Presentation.DTOs.DepartmentDto;
import org.example.Presentation.DTOs.EmployeeDto;
import org.example.Presentation.DTOs.PayDayDTO;
import org.example.Presentation.Mapper.EmployeeMapper;

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
            Employee employee = null;
            try{
                 employee = employeeDAO.getEmployeeByPhoneNumber(phoneNumber);
            }
            catch (Exception e){
                throw new IllegalArgumentException("Employee not found");
            }
            return EmployeeMapper.instance.toEmployeeDto(employee);
        });
    }

    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        return Database.doInTransaction(entityManager -> {
            Employee employee = EmployeeMapper.instance.toEmployee(employeeDto);
            //set department
            DepartmentDAO departmentDAO = new DepartmentDAO(entityManager);
            Department department = departmentDAO.getDepartmentByName(employeeDto.getDepartmentName());
            if (department == null){

                throw new IllegalArgumentException("Department does not exist");

            }
            employee.setDepartment(departmentDAO.getDepartmentByName(employeeDto.getDepartmentName()));
            //set job
            JobDAO jobDAO = new JobDAO(entityManager);
            Job job = jobDAO.getJobByTitle(employeeDto.getJobTitle());
            if (job == null){

                throw new IllegalArgumentException("Job does not exist");

            }
            employee.setJob(job);
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
            return EmployeeMapper.instance.toEmployeeDto(employee);
        });
    }
    public boolean removeEmployee(String email){
      return  Database.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);
            Employee employee = employeeDAO.getEmployeeByEmail(email);
            List<DepartmentDto> departments = new DepartmentServices().getDepartmentByManager(email);
            if (departments != null) {
                for (DepartmentDto department : departments) {
                    new DepartmentServices().removeManager(department.getDepartmentName());
                }
            }
            employee.setRemoved(true);
            return true;
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
                throw new IllegalArgumentException("Employee not found");
            }
            Optional<Attendance> check = new AttendanceDAO(entityManager).getLastAttendance(employee);
            if (check.isPresent() && check.get().getDate().equals(LocalDate.now())) {
                throw new IllegalArgumentException("Already checked in");
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
                throw new IllegalArgumentException("Employee not found");
            }
            Optional<Attendance> check = new AttendanceDAO(entityManager).getLastAttendance(employee);
            if (check.isEmpty() || (check.get().getDate().equals(LocalDate.now()) && check.get().getCheckOutTime() != null)){
                throw new IllegalArgumentException("Already checked out");
            }
            AttendanceDAO attendanceDao = new AttendanceDAO(entityManager);
            Optional<Attendance> attendance = attendanceDao.getLastAttendance(employee);
            attendance.get().setCheckOutTime(LocalTime.now());
            return true;
        });
    }

    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        return Database.doInTransaction(entityManager -> {

            EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);
            Employee employee = employeeDAO.getEmployeeByEmail(employeeDto.getEmail());
            if (employee == null){
                throw new IllegalArgumentException("Employee does not exist");
            }
            if(employeeDto.getFirstName()!=null){
                employee.setFirstName(employeeDto.getFirstName());
            }
            if(employeeDto.getLastName()!=null){
                employee.setLastName(employeeDto.getLastName());
            }
            if(employeeDto.getEmail()!=null){
                employee.setEmail(employeeDto.getEmail());
            }
            if(employeeDto.getPhoneNumber()!=null){
                employee.setPhoneNumber(employeeDto.getPhoneNumber());
            }
            if(employeeDto.getHireDate()!=null){
                employee.setHireDate(employeeDto.getHireDate());
            }
            if(employeeDto.getVacationDays()!=null){
                employee.setVacationDays(employeeDto.getVacationDays());
            }
            if(employeeDto.getStreet()!=null){
                employee.getAddress().setStreetAddress(employeeDto.getStreet());
            }
            if(employeeDto.getCity()!=null){
                employee.getAddress().setCity(employeeDto.getCity());
            }
            if(employeeDto.getCountry()!=null){
                employee.getAddress().setCountry(employeeDto.getCountry());
            }
            if(employeeDto.getSalaryAmount()!=null){
                employee.getSalary().setSalaryAmount(employeeDto.getSalaryAmount());
            }
            if(employeeDto.getJobTitle()!=null){
                Job job = new JobDAO(entityManager).getJobByTitle(employeeDto.getJobTitle());
                if (job == null){
                    throw new IllegalArgumentException("Job does not exist");
                }
                employee.setJob(job);
            }
            if(employeeDto.getDepartmentName()!=null){
                Department department = new DepartmentDAO(entityManager).getDepartmentByName(employeeDto.getDepartmentName());
                if (department == null){
                    throw new IllegalArgumentException("Department does not exist");
                }
                employee.setDepartment(department);
            }
            if(employeeDto.getManagerEmail()!=null){
                Employee manager = employeeDAO.getEmployeeByEmail(employeeDto.getManagerEmail());
                if (manager == null){
                    throw new IllegalArgumentException("Manager does not exist");
                }
                employee.setManager(manager);
            }
            employeeDAO.update(employee);
            return EmployeeMapper.instance.toEmployeeDto(employee);
        });
    }
}
