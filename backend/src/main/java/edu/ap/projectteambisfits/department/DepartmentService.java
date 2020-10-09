package edu.ap.projectteambisfits.department;

import java.util.List;

public interface DepartmentService {

    List<Department> findAll();

    Department saveDepartment(Department department);

    Department findById(String departmentId);

}