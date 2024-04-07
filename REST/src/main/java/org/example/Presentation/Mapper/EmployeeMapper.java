package org.example.Presentation.Mapper;

import org.example.Persistence.Entities.Employee;
import org.example.Presentation.DTOs.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper instance = Mappers.getMapper(EmployeeMapper.class);
    Employee toEmployee(EmployeeDto employeeDto);
    EmployeeDto toEmployeeDto(Employee employee);
    List<EmployeeDto> toEmployeeDtoList(List<Employee> employees);
}
