package org.example.Persistence.DAOS.Implementation;

import jakarta.persistence.EntityManager;
import org.example.Persistence.DAOS.Generic.dao;
import org.example.Persistence.Entities.Vacation;

import java.util.List;

public class VacationDAO extends dao {
    public VacationDAO(EntityManager entityManager) {
        super(entityManager);
        super.setType(Vacation.class);
    }

    public List<Vacation> getVacationByEmail(String email) {
        return entityManager.createQuery("SELECT v FROM Vacation v WHERE v.employee.email = :email and v.status = :Approved", Vacation.class)
                .setParameter("email", email)
                .getResultList();
    }

    public List<Vacation> getVacationByStatus(String status) {
        return entityManager.createQuery("SELECT v FROM Vacation v WHERE v.status = :status", Vacation.class)
                .setParameter("status", status)
                .getResultList();
    }
    public Vacation getLastPendingVacation(String email) {
        return entityManager.createQuery("SELECT v FROM Vacation v WHERE v.employee.email = :email and v.status = :Pending", Vacation.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
