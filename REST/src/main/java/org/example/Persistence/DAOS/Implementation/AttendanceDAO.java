package org.example.Persistence.DAOS.Implementation;

import jakarta.persistence.EntityManager;
import org.example.Persistence.DAOS.Generic.dao;
import org.example.Persistence.Entities.Attendance;
import org.example.Persistence.Entities.Employee;

import java.util.List;
import java.util.Optional;

public class AttendanceDAO  extends dao<Attendance> {
    public AttendanceDAO(EntityManager entityManager) {
        super(entityManager);
        super.setType(Attendance.class);
    }
    public Optional<Attendance> getLastAttendance(Employee employee) {
        List<Attendance> attendances= entityManager.createQuery("SELECT a FROM Attendance a WHERE a.employee = :employee ORDER BY a.id DESC", Attendance.class)
                .setParameter("employee", employee)
                .setMaxResults(1)
                .getResultList();
        if (attendances.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(attendances.get(0));

    }
}
