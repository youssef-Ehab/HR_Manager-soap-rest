package org.example.Services;

import org.example.Persistence.DAOS.Implementation.EmployeeDAO;
import org.example.Persistence.DAOS.Implementation.PerformanceReviewDAO;
import org.example.Persistence.Database;
import org.example.Persistence.Entities.Employee;
import org.example.Persistence.Entities.PerformanceReview;
import org.example.Presentation.DTOs.PerformanceReviewDto;
import org.example.Presentation.Mapper.PerformanceReviewMapper;

import java.util.List;

public class PerformanceReviewServices {
    public List<PerformanceReviewDto> getAllPerformanceReviews() {
        return Database.doInTransaction(entityManager -> {
            PerformanceReviewDAO performanceReviewDAO = new PerformanceReviewDAO(entityManager);
            return PerformanceReviewMapper.INSTANCE.toPerformanceReviewDtoList(performanceReviewDAO.getAll());
        });
    }

    public List<PerformanceReviewDto> getPerformanceReviewByEmail(String email) {
        return Database.doInTransaction(entityManager -> {
            PerformanceReviewDAO performanceReviewDAO = new PerformanceReviewDAO(entityManager);
            return PerformanceReviewMapper.INSTANCE.toPerformanceReviewDtoList(performanceReviewDAO.getPerformanceReviewByEmail(email));
        });
    }

    public List<PerformanceReviewDto> getPerformanceReviewByRating(int rating) {
        return Database.doInTransaction(entityManager -> {
            PerformanceReviewDAO performanceReviewDAO = new PerformanceReviewDAO(entityManager);
            return PerformanceReviewMapper.INSTANCE.toPerformanceReviewDtoList(performanceReviewDAO.getPerformanceReviewByRating(rating));
        });
    }

    public List<PerformanceReviewDto> getAllActive() {
        return Database.doInTransaction(entityManager -> {
            PerformanceReviewDAO performanceReviewDAO = new PerformanceReviewDAO(entityManager);
            return PerformanceReviewMapper.INSTANCE.toPerformanceReviewDtoList(performanceReviewDAO.getAllActive());
        });
    }

    public List<PerformanceReviewDto> getAllRemoved() {
        return Database.doInTransaction(entityManager -> {
            PerformanceReviewDAO performanceReviewDAO = new PerformanceReviewDAO(entityManager);
            return PerformanceReviewMapper.INSTANCE.toPerformanceReviewDtoList(performanceReviewDAO.getAllRemoved());
        });
    }

    public List<PerformanceReviewDto> getPerformanceReviewByReviewer(String email) {
        return Database.doInTransaction(entityManager -> {
            PerformanceReviewDAO performanceReviewDAO = new PerformanceReviewDAO(entityManager);
            return PerformanceReviewMapper.INSTANCE.toPerformanceReviewDtoList(performanceReviewDAO.getPerformanceReviewByReviewer(email));
        });
    }

    public boolean createPerformanceReview(PerformanceReviewDto performanceReviewDto) {
     String email = performanceReviewDto.getEmployeeEmail();
     String reviewerEmail = performanceReviewDto.getReviewerEmail();
     return Database.doInTransaction(entityManager -> {
         EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);
         Employee employee = employeeDAO.getEmployeeByEmail(email);
         Employee reviewer = employeeDAO.getEmployeeByEmail(reviewerEmail);
            if(reviewer == null){
                throw new IllegalArgumentException("Reviewer not found");
            }
            if (employee == null)
                throw new IllegalArgumentException("Employee not found");
            if (employee.getManager() != reviewer)
                throw new IllegalArgumentException("Reviewer is not the manager of the employee");

            PerformanceReviewDAO performanceReviewDAO = new PerformanceReviewDAO(entityManager);
         PerformanceReview performanceReview = PerformanceReviewMapper.INSTANCE.toPerformanceReview(performanceReviewDto);
            performanceReview.setEmployee(employee);
            performanceReview.setReviewer(reviewer);
            entityManager.persist(performanceReview);
            return true;
        });
    }
}
