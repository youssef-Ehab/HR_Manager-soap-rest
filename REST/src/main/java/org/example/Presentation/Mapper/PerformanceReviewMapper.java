package org.example.Presentation.Mapper;

import org.example.Persistence.Entities.PerformanceReview;
import org.example.Presentation.DTOs.PerformanceReviewDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PerformanceReviewMapper {
    PerformanceReviewMapper INSTANCE = Mappers.getMapper(PerformanceReviewMapper.class);

    PerformanceReview toPerformanceReview(PerformanceReviewDto performanceReviewDto);
    PerformanceReviewDto toPerformanceReviewDto(PerformanceReview performanceReview);

    List<PerformanceReviewDto> toPerformanceReviewDtoList(List<PerformanceReview> performanceReviews);


}
