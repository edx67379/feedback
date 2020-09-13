package ru.omsu.imit.main.dao;

import ru.omsu.imit.main.models.Department;
import ru.omsu.imit.main.models.Employee;
import ru.omsu.imit.main.models.Organization;

import java.util.List;

public interface DepartmentDAO {
    public Department insert(Organization organization, Department department);

    public Department getById(int id);

    public List<Department> getByOrganization(Organization organization);

    public Department getByEmployee(Employee employee);

    public List<Department> getByName(String name);

    public List<Department> getAllLazy();

    public void deleteAll();

    public void delete(Department department);

    public void changeName(Department department, String name);
}
