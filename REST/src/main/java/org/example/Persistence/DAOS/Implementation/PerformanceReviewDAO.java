package org.example.Persistence.DAOS.Implementation;

import jakarta.persistence.EntityManager;
import org.example.Persistence.DAOS.Generic.dao;
import org.example.Persistence.Entities.PerformanceReview;

import java.util.List;

public class PerformanceReviewDAO extends dao {
    public PerformanceReviewDAO(EntityManager entityManager) {
        super(entityManager);
        super.setType(PerformanceReview.class);
    }

    public List<PerformanceReview> getPerformanceReviewByEmail(String email) {
        return entityManager.createQuery("SELECT pr FROM PerformanceReview pr WHERE pr.employee.email = :email and pr.employee.removed= false", PerformanceReview.class)
                .setParameter("email", email)
                .getResultList();
    }

    public List<PerformanceReview> getPerformanceReviewByRating(int rating) {
        return entityManager.createQuery("SELECT pr FROM PerformanceReview pr WHERE pr.rating = :rating and pr.employee.removed= false", PerformanceReview.class)
                .setParameter("rating", rating)
                .getResultList();
    }
    public List<PerformanceReview> getAllActive() {
        return entityManager.createQuery("SELECT pr FROM PerformanceReview pr WHERE pr.employee.removed= false", PerformanceReview.class)
                .getResultList();
    }
    public List<PerformanceReview> getAllRemoved() {
        return entityManager.createQuery("SELECT pr FROM PerformanceReview pr WHERE pr.employee.removed= true", PerformanceReview.class)
                .getResultList();
    }

    public List<PerformanceReview> getPerformanceReviewByReviewer(String email) {
        return entityManager.createQuery("SELECT pr FROM PerformanceReview pr WHERE pr.reviewer.email = :email ", PerformanceReview.class)
                .setParameter("email", email)
                .getResultList();
    }
}
