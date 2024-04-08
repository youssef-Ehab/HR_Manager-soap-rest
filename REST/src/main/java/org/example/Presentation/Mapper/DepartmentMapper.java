package org.example.Presentation.Mapper;

import org.example.Persistence.Entities.Department;
import org.example.Presentation.DTOs.DepartmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper instance = Mappers.getMapper(DepartmentMapper.class);
    Department toDepartment(DepartmentDto departmentDto);
    DepartmentDto toDepartmentDto(Department department);
    List<DepartmentDto> toDepartmentDtoList(List<Department> departments);
}
