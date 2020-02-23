package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentJdbcDaoImpl implements DepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentJdbcDaoImpl.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    MapSqlParameterSource parameterSource = new MapSqlParameterSource();

    @Value("${DEP.sqlGetAllDepartmentBy}")
    private String sqlGetAllDepartmentBy;

    @Value("${DEP.sqlAdd}")
    private String sqlAdd;

    @Value("${DEP.sqlUpdate}")
    private String sqlUpdate;

    @Value("${DEP.sqlCountById}")
    private String sqlCountById;

    @Value("${DEP.sqlCountByName}")
    private String sqlCountByName;

    @Value("${DEP.sqlGetDepartmentById}")
    private String sqlGetDepartmentById;

    @Value("${DEP.sqlDeleteById}")
    private String sqlDeleteById;

    public DepartmentJdbcDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }



    @Override
    public List<Department> getDepartments() {

        LOGGER.debug("The getDepartment method started" );
        List<Department> departments = namedParameterJdbcTemplate.query(sqlGetAllDepartmentBy, new DepartmentRowMapper());

        return departments;
    }




    @Override
    public Department getDepartmentById(Integer departmentId) {
        LOGGER.debug("DepartmentId = :   {}" , departmentId);

        parameterSource.addValue("departmentId", departmentId);
        Integer result = namedParameterJdbcTemplate.queryForObject(sqlCountById, parameterSource, Integer.class);

        if (result == 0) {
            LOGGER.error("DepartmentJdbcDaoImpl getDepartmentById - Record is absent");
            throw new IllegalArgumentException("Record is absent");
        }

        Department department = (Department) namedParameterJdbcTemplate.queryForObject(sqlGetDepartmentById, parameterSource,
                new DepartmentRowMapper());

        return department;
    }



    @Override
    public Department addDepartment(Department department) {
        LOGGER.debug("DepartmentJdbcDaoImpl addDepartment {}", department);
        parameterSource.addValue( "departmentName", department.getDepartmentName());

        Integer result = namedParameterJdbcTemplate.queryForObject(sqlCountByName, parameterSource, Integer.class);
        LOGGER.debug("result {}", result);

        if (result == 0) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sqlAdd, parameterSource, keyHolder);
            department.setDepartmentId(keyHolder.getKey().intValue());
            LOGGER.debug("DepartmentJdbcDaoImpl after adding addDepartment {}", department);
            return department;
        } else {
            LOGGER.error("Record with this name is present");
            throw new IllegalArgumentException("Record with this name is present");
        }
    }



    @Override
    public void updateDepartment(Department department) {
    LOGGER.debug("NewDepartment {}" ,department);
    parameterSource.addValue("departmentName",department.getDepartmentName());
    namedParameterJdbcTemplate.update(sqlUpdate,parameterSource);
    }



    @Override
    public void deleteDepartment(Integer departmentId) {
        LOGGER.debug("DepartmentId = :      " + departmentId);
        parameterSource.addValue("departmentId", departmentId);
        namedParameterJdbcTemplate.update(sqlDeleteById, parameterSource);
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
