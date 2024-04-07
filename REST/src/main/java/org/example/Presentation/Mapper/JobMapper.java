package org.example.Presentation.Mapper;

import org.example.Persistence.Entities.Job;
import org.example.Presentation.DTOs.JobDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface JobMapper {
    JobMapper instance = org.mapstruct.factory.Mappers.getMapper(JobMapper.class);

    Job toJob(JobDto jobDto);
    JobDto toJobDto(Job job);
    List<JobDto> toJobDtoList(List<Job> jobs);

}
