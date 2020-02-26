package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Department;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao.xml"})
public class DepartmentJdbcDaoImplTest {

    Department department = new Department();
    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void getDepartments() {
        List<Department> departments = departmentDao.getDepartments();
        assertNotNull(departments);
    }

    @Test
    public void getDepartmentById() {
        Department department = departmentDao.getDepartmentById(2);
        assertNotNull(department);
    }


    @Test
    public void getDepartmentByIdNegativeTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            departmentDao.getDepartmentById(5);
        });
    }

    @Test
    public void addDepartmentTest() {
        department.setDepartmentName("Management");

        int sizeBefore = departmentDao.getDepartments().size();
        Department departmentOut = departmentDao.addDepartment(department);
        int sizeAfter = departmentDao.getDepartments().size();

        assertTrue(sizeBefore + 1 == sizeAfter);
        assertNotNull(departmentOut);
        assertTrue(departmentOut.getDepartmentName().equals("Management"));

    }



    @Test
    public void updateDepartment() {
        department = departmentDao.getDepartmentById(2);
        department.setDepartmentName("NewDepartment");
        departmentDao.updateDepartment(department);

        assertTrue(department.getDepartmentName().equals("NewDepartment"));
    }

    @Test
    public void deleteDepartment() {

        Collection<Department> departments = departmentDao.getDepartments();
        int sizeBefore = departments.size();
        System.out.println("Size before = :         " + sizeBefore);
//        department = departmentDao.getDepartmentById(1);
        departmentDao.deleteDepartment(1);
        departments = departmentDao.getDepartments();
        int sizeAfter = departments.size();
        System.out.println("Size after = :         " + sizeAfter);
        assert(sizeBefore > sizeAfter);
    }

}