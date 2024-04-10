package org.example.Services;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.example.Persistence.DAOS.Implementation.DepartmentDAO;
import org.example.Persistence.DAOS.Implementation.EmployeeDAO;
import org.example.Persistence.Database;
import org.example.Persistence.Entities.Department;
import org.example.Persistence.Entities.Employee;
import org.example.Presentation.DTOs.EmployeeDto;
import org.example.Presentation.DTOs.DepartmentDto;
import org.example.Presentation.Mapper.DepartmentMapper;

import java.util.List;
import java.util.Set;

public class DepartmentServices {

    public Set<String> getAllEmployeesFromDepartment(int depNum) {
        Set<String> employeeNames =  Database.doInTransaction(entityManager -> {
            DepartmentDAO departmentDAO = new DepartmentDAO(entityManager);
            Department department = departmentDAO.get(depNum).get();
            DepartmentDto departmentDto = DepartmentMapper.instance.toDepartmentDto(department);
            Set<String> employees = departmentDto.getEmployeeNames();
            return employees;
        });
        return employeeNames;
    }
    public Set<String> getAllEmployeesFromDepartment(String depname) {
        Set<String> employeeNames =  Database.doInTransaction(entityManager -> {
            DepartmentDAO departmentDAO = new DepartmentDAO(entityManager);
            Department department = departmentDAO.getDepartmentByName(depname);
            DepartmentDto departmentDto = DepartmentMapper.instance.toDepartmentDto(department);
            Set<String> employees = departmentDto.getEmployeeNames();
            return employees;
        });
        return employeeNames;
    }

    public boolean createDepartment(List<String> data) {
        if (data.isEmpty() || data.size() > 2 || data.get(0).equals("")){
            throw new IllegalArgumentException("Invalid data");
        }
        if (data.size() < 2) {
            data.add("no Manager");
        }
        String depName = data.get(0);
        String managerEmail = data.get(1);
        return Database.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);
            Employee manager = new Employee();
            if ( depName == null) {
                throw new IllegalArgumentException("Department name is null");
            }
            if(managerEmail != null){
                manager = employeeDAO.getEmployeeByEmail(managerEmail);
            }
            DepartmentDAO departmentDAO = new DepartmentDAO(entityManager);
            if (departmentDAO.getDepartmentByName(depName) != null) {
                throw new IllegalArgumentException("Department already exists");
            }
            Department department = new Department();
            department.setDepartmentName(depName);
            department.setManager(manager);
            entityManager.persist(department);
            return true;
        });
    }

    public boolean setManager(String depName, String email) {
        return Database.doInTransaction(entityManager -> {
            DepartmentDAO departmentDAO = new DepartmentDAO(entityManager);
            Department department = departmentDAO.getDepartmentByName(depName);
            EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);
            Employee manager = employeeDAO.getEmployeeByEmail(email);
            if (department == null ) {
                throw new IllegalArgumentException("Department not found");
            }
            if (manager == null) {
                throw new IllegalArgumentException("Manager not found");
            }
            department.setManager(manager);
            entityManager.persist(department);
            return true;
        });
    }


    public List<DepartmentDto> getDepartmentByManager(String email) {
        return Database.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);
            Employee manager = employeeDAO.getEmployeeByEmail(email);
            if (manager == null) {
                throw new IllegalArgumentException("Manager not found");
            }
            DepartmentDAO departmentDAO = new DepartmentDAO(entityManager);
            List<Department> departments = departmentDAO.getDepartmentByManager(manager);
            return DepartmentMapper.instance.toDepartmentDtoList(departments);
        });
    }

    public boolean removeManager(String depName) {
        return Database.doInTransaction(entityManager -> {
            DepartmentDAO departmentDAO = new DepartmentDAO(entityManager);
            Department department = departmentDAO.getDepartmentByName(depName);
            if (department == null ) {
                throw new IllegalArgumentException("Department not found");
            }
            department.setManager(null);
            entityManager.persist(department);
            return true;
        });
    }
}
