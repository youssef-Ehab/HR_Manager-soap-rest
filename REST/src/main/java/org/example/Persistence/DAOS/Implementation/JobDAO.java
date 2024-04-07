package org.example.Persistence.DAOS.Implementation;

import jakarta.persistence.EntityManager;
import org.example.Persistence.DAOS.Generic.dao;
import org.example.Persistence.Entities.Job;

import java.util.List;

public class JobDAO extends dao<Job> {
    public JobDAO(EntityManager entityManager) {
        super(entityManager);
        super.setType(Job.class);
    }
    public Job getJobByTitle(String title) {
        List<Job> jobs = entityManager.createQuery("SELECT j FROM Job j WHERE j.jobTitle = :title", Job.class)
                .setParameter("title", title)
                .getResultList();

        if (jobs.isEmpty()) {
            return null;
        } else {
            return jobs.get(0);
        }
    }
}
