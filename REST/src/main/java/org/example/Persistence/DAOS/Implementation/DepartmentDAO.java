package org.example.Persistence.DAOS.Implementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.Persistence.DAOS.Generic.dao;
import org.example.Persistence.Entities.Department;

public class DepartmentDAO extends dao<Department> {
    public DepartmentDAO(EntityManager entityManager) {
        super(entityManager);
        super.setType(Department.class);
    }
    public Department getDepartmentByName(String name) {
        if (name == null) {
            return null;
        }

        try {
            return entityManager.createQuery("SELECT d FROM Department d WHERE d.departmentName = :name", Department.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
