package com.epam.brest.courses.rest_app.exception;

public class DepartmentNotFoundException extends RuntimeException {

    public DepartmentNotFoundException(Integer id) {
        super("Department id not found : " + id);
    }

}
