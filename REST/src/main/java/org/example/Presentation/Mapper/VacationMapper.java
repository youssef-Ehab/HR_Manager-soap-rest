package org.example.Presentation.Mapper;

import org.example.Persistence.Entities.Vacation;
import org.example.Presentation.DTOs.VacationDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface VacationMapper {
    VacationMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(VacationMapper.class);
    VacationDto vacationToVacationDto(Vacation vacation);
    Vacation vacationDtoToVacation(VacationDto vacationDto);
    List<VacationDto> vacationsToVacationDtos(List<Vacation> vacations);
}
