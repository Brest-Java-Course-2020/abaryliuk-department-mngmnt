package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Department;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentJdbcDaoImpl implements DepartmentDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public DepartmentJdbcDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Department> getDepartments() {
        List<Department> departments = namedParameterJdbcTemplate
                .query("SELECT d.departmentId, d.departmentName FROM department d ORDER BY d.departmentName",
                        new DepartmentRowMapper());
        return departments;
    }


    @Override
    public Department getDepartmentById(Integer departmentId) {

        String str = "SELECT * FROM department WHERE departmentId = :departmentId";

        SqlParameterSource namedParameters = new MapSqlParameterSource("departmentId", Integer.valueOf(departmentId));
        Department department = (Department) namedParameterJdbcTemplate.queryForObject(str, namedParameters, new DepartmentRowMapper());
        return department;
    }

    @Override
    public Department addDepartment(Department department) {
        return null;
    }

    @Override
    public void updateDepartment(Department department) {

    }

    @Override
    public void deleteDepartment(Integer departmentId) {

    }

    private class DepartmentRowMapper implements RowMapper<Department> {
        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt("DEPARTMENTID"));
            department.setDepartmentName(resultSet.getString("DEPARTMENTNAME"));
            return department;
        }
    }
}
