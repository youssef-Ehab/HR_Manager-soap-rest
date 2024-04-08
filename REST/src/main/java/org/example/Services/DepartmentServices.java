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
        if (data.isEmpty() || data.size() > 2 || data.get(0) .equals("")){
            return false;
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
                return false;
            }
            if(managerEmail != null){
                manager = employeeDAO.getEmployeeByEmail(managerEmail);
            }
            DepartmentDAO departmentDAO = new DepartmentDAO(entityManager);
            if (departmentDAO.getDepartmentByName(depName) != null) {
                return false;
            }
            Department department = new Department();
            department.setDepartmentName(depName);
            department.setManager(manager);
            entityManager.persist(department);
            return true;
        });
    }
}
