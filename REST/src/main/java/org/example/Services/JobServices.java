package org.example.Services;

import org.example.Persistence.DAOS.Implementation.DepartmentDAO;
import org.example.Persistence.DAOS.Implementation.JobDAO;
import org.example.Persistence.Database;
import org.example.Persistence.Entities.Department;
import org.example.Persistence.Entities.Job;
import org.example.Presentation.DTOs.JobDto;
import org.example.Presentation.Mapper.JobMapper;

import java.math.BigDecimal;
import java.util.List;

public class JobServices {
    public List<JobDto> getAllJobs() {
        return Database.doInTransaction(entityManager -> {
            JobDAO jobDAO = new JobDAO(entityManager);
            List<Job> jobs= jobDAO.getAll();
            return JobMapper.instance.toJobDtoList(jobs);
        });
    }

    public JobDto getJobByTitle(String title) {
        return Database.doInTransaction(entityManager -> {
            JobDAO jobDAO = new JobDAO(entityManager);
            Job job = jobDAO.getJobByTitle(title);
            return JobMapper.instance.toJobDto(job);
        });
    }

    public boolean createJob(JobDto jobDto) {
        return Database.doInTransaction(entityManager -> {
            String title = jobDto.getJobTitle();
            BigDecimal minSalary = jobDto.getMinSalary();
            BigDecimal maxSalary = jobDto.getMaxSalary();
            String department = jobDto.getDepartmentName();
            JobDAO jobDAO = new JobDAO(entityManager);
            if ( title == null || minSalary == null || maxSalary == null || department == null) {
                return false;
            }
            if (jobDAO.getJobByTitle(title) != null) {
                return false;
            }
            DepartmentDAO departmentDAO = new DepartmentDAO(entityManager);
            Department dep = departmentDAO.getDepartmentByName(department);
            if (dep == null) {
                return false;
            }
            Job job = new Job();
            job.setJobTitle(title);
            job.setMinSalary(minSalary);
            job.setMaxSalary(maxSalary);
            job.setDepartment(dep);
            entityManager.persist(job);
            return true;
        });
    }
}
