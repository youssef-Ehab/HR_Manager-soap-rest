package org.example.Services;

import org.example.Persistence.DAOS.Implementation.EmployeeDAO;
import org.example.Persistence.DAOS.Implementation.VacationDAO;
import org.example.Persistence.Database;
import org.example.Persistence.Entities.Employee;
import org.example.Persistence.Entities.Vacation;
import org.example.Presentation.DTOs.VacationDto;
import org.example.Presentation.Mapper.VacationMapper;

import java.time.LocalDate;
import java.util.List;

public class VacationServices {
    public List<VacationDto> getAllVacations() {
        return Database.doInTransaction(entityManager -> {
            VacationDAO vacationDAO = new VacationDAO(entityManager);
            return VacationMapper.INSTANCE.vacationsToVacationDtos(vacationDAO.getAll());
        });
    }

    public List<VacationDto> getVacationByEmail(String email) {
        return Database.doInTransaction(entityManager -> {
            VacationDAO vacationDAO = new VacationDAO(entityManager);
            return VacationMapper.INSTANCE.vacationsToVacationDtos(vacationDAO.getVacationByEmail(email));
        });
    }

    public List<VacationDto> getVacationByStatus(String status) {
        return Database.doInTransaction(entityManager -> {
            VacationDAO vacationDAO = new VacationDAO(entityManager);
            return VacationMapper.INSTANCE.vacationsToVacationDtos(vacationDAO.getVacationByStatus(status));
        });
    }

    public boolean acceptVacation(String email) {
        return Database.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);
            Employee employee = employeeDAO.getEmployeeByEmail(email);
            if (employee == null) {
                return false;
            }
            VacationDAO vacationDAO = new VacationDAO(entityManager);
            Vacation vacation = vacationDAO.getLastPendingVacation(email);
            //get the number of days between the start and end date of the vacation
            LocalDate startDate = vacation.getStartDate();
            LocalDate endDate = vacation.getEndDate();
            long daysBetween = startDate.until(endDate).getDays();
            int vacationDays = employee.getVacationDays();
            if (daysBetween > vacationDays) {
                return false;
            }
            employee.setVacationDays(vacationDays - (int) daysBetween);
            entityManager.persist(employee);
            vacation.setStatus("Approved");
            entityManager.persist(vacation);
            return true;

        });
    }

    public boolean rejectVacation(String email) {
        return Database.doInTransaction(entityManager -> {
            VacationDAO vacationDAO = new VacationDAO(entityManager);
            Vacation vacation = vacationDAO.getLastPendingVacation(email);
            vacation.setStatus("Rejected");
            entityManager.persist(vacation);
            return true;
        });
    }
}
