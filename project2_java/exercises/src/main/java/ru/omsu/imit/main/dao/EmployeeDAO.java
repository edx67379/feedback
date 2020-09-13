package ru.omsu.imit.main.dao;

import ru.omsu.imit.main.models.Department;
import ru.omsu.imit.main.models.Employee;

import java.util.List;

public interface EmployeeDAO {
    public Employee insert(Department department, Employee employee);

    public Employee getById(int id);

    public List<Employee> getByDepartment(Department department);

    public List<Employee> getByName(String name);

    public List<Employee> getAllLazy();

    public void deleteAll();

    public void delete(Employee employee);

    public void changeName(Employee employee, String name);
}
