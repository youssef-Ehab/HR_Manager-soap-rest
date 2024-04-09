package org.example.Controllers.SOAP.Implementation;

import jakarta.jws.WebService;
import org.example.Controllers.SOAP.Interfaces.PerformanceReviewWebService;
import org.example.Presentation.DTOs.PerformanceReviewDto;
import org.example.Services.PerformanceReviewServices;

import java.util.List;

@WebService(endpointInterface = "org.example.Controllers.SOAP.Interfaces.PerformanceReviewWebService")
public class PerformanceReviewWebServiceImpl implements PerformanceReviewWebService {

    private final PerformanceReviewServices performanceReviewServices = new PerformanceReviewServices();

    @Override
    public List<PerformanceReviewDto> getAllPerformanceReviews() {
        return performanceReviewServices.getAllPerformanceReviews();
    }

    @Override
    public List<PerformanceReviewDto> getPerformanceReviewByEmail(String email) {
        return performanceReviewServices.getPerformanceReviewByEmail(email);
    }

    @Override
    public List<PerformanceReviewDto> getPerformanceReviewByRating(int rating) {
        return performanceReviewServices.getPerformanceReviewByRating(rating);
    }

    @Override
    public List<PerformanceReviewDto> getAllActive() {
        return performanceReviewServices.getAllActive();
    }

    @Override
    public List<PerformanceReviewDto> getAllRemoved() {
        return performanceReviewServices.getAllRemoved();
    }

    @Override
    public List<PerformanceReviewDto> getPerformanceReviewByReviewer(String email) {
        return performanceReviewServices.getPerformanceReviewByReviewer(email);
    }

    @Override
    public boolean createPerformanceReview(PerformanceReviewDto performanceReviewDto) {
        return performanceReviewServices.createPerformanceReview(performanceReviewDto);
    }
}
