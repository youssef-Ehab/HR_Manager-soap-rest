package org.example.Controllers.REST;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.example.Presentation.DTOs.PerformanceReviewDto;
import org.example.Services.PerformanceReviewServices;

import java.util.List;

@Path("/performance")
public class PerformanceReviewController {
    PerformanceReviewServices services = new PerformanceReviewServices();
    @GET
    @Path("/all")
    public List<PerformanceReviewDto> getAllPerformanceReviews() {
        return services.getAllPerformanceReviews();
    }
    @GET
    @Path("/email/{email}")
    public List<PerformanceReviewDto> getPerformanceReviewByEmail(@PathParam("email") String email) {
        return services.getPerformanceReviewByEmail(email);
    }
    @GET
    @Path("/rating/{rating}")
    public List<PerformanceReviewDto> getPerformanceReviewByRating(@PathParam("rating") int rating) {
        return services.getPerformanceReviewByRating(rating);
    }
    @GET
    @Path("/active")
    public List<PerformanceReviewDto> getAllActive() {
        return services.getAllActive();
    }
    @GET
    @Path("/removed")
    public List<PerformanceReviewDto> getAllRemoved() {
        return services.getAllRemoved();
    }
    @GET
    @Path("/reviewer/{email}")
    public List<PerformanceReviewDto> getPerformanceReviewByReviewer(@PathParam("email") String email) {
        return services.getPerformanceReviewByReviewer(email);
    }
    @POST
    @Path("/create")
    public boolean createPerformanceReview(PerformanceReviewDto performanceReviewDto) {
        return services.createPerformanceReview(performanceReviewDto);

    }
}
