package com.epam.brest.courses.service;

import com.epam.brest.courses.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    /**
     * Find all departments.
     *
     * @return departments list.
     */

    public List<Department> findAll();

    /**
     * Find department by Id.
     *
     * @param departmentId department Id.
     * @return department.
     */

    public Optional<Department> findById(Integer departmentId);

    /**
     * Persist new department.
     *
     * @param department department.
     * @return persisted department id.
     */

    public Integer create(Department department);

    /**
     * Update department.
     *
     * @param department department.
     * @return number of updated records in the database.
     */

    public int update(Department department);

    /**
     * Delete department.
     *
     * @param departmentId department id.
     * @return number of updated records in the database.
     */

    public int delete(Integer departmentId);
}

