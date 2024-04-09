package org.example.Controllers.SOAP.Interfaces;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.example.Presentation.DTOs.PerformanceReviewDto;

import java.util.List;

@WebService
public interface PerformanceReviewWebService {

    @WebMethod(operationName = "getAllPerformanceReviews")
    List<PerformanceReviewDto> getAllPerformanceReviews();

    @WebMethod(operationName = "getPerformanceReviewByEmail")
    List<PerformanceReviewDto> getPerformanceReviewByEmail(@WebParam(name = "email") String email);

    @WebMethod(operationName = "getPerformanceReviewByRating")
    List<PerformanceReviewDto> getPerformanceReviewByRating(@WebParam(name = "rating") int rating);

    @WebMethod(operationName = "getAllActive")
    List<PerformanceReviewDto> getAllActive();

    @WebMethod(operationName = "getAllRemoved")
    List<PerformanceReviewDto> getAllRemoved();

    @WebMethod(operationName = "getPerformanceReviewByReviewer")
    List<PerformanceReviewDto> getPerformanceReviewByReviewer(@WebParam(name = "email") String email);

    @WebMethod(operationName = "createPerformanceReview")
    boolean createPerformanceReview(@WebParam(name = "performanceReviewDto") PerformanceReviewDto performanceReviewDto);
}
