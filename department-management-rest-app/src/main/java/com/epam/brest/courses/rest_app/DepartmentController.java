package com.epam.brest.courses.rest_app;

import com.epam.brest.courses.model.Department;
import com.epam.brest.courses.model.dto.DepartmentDto;
import com.epam.brest.courses.rest_app.exception.DepartmentNotFoundException;
import com.epam.brest.courses.service.DepartmentDtoService;
import com.epam.brest.courses.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;

/**
 * Department controller.
 */
@RestController
@EnableSwagger2
@RequestMapping("departments")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentDtoService departmentDtoService;
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentDtoService departmentDtoService, DepartmentService departmentService) {
        this.departmentDtoService = departmentDtoService;
        this.departmentService = departmentService;
    }

    /**
     * Goto departments list page.
     *
     * @return view name.
     */
    @GetMapping(value = "/")
    public final Collection<DepartmentDto> departments() {

        LOGGER.debug("departments()");
        return departmentDtoService.findAllWithAvgSalary();
    }

    /**
     *Find department by id.
     * @param id
     * @return Department.
     */
    @GetMapping("/{id}")
    public Department findById(@PathVariable Integer id) {

        LOGGER.debug("find department by id({})", id);
        return departmentService.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    /**
     * Add department by departmentName.
     * @param departmentName
     * @return new Department().
     */
    @PostMapping(path = "/addByName", consumes = "application/json", produces = "application/json")
    public Integer add(@RequestBody String departmentName) {
        LOGGER.debug("add department with name({})", departmentName);
        return departmentService.create(new Department(departmentName));
    }

    /**
     * Add department with requestBody = Department department.
     * @param department
     * @returnnew Department().
     */
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public Integer add(@RequestBody Department department) {
        LOGGER.debug("add department ({})", department);
        return departmentService.create(department);
    }

}

