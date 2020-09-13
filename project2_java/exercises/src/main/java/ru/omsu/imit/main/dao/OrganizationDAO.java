package ru.omsu.imit.main.dao;

import ru.omsu.imit.main.models.Department;
import ru.omsu.imit.main.models.Organization;

import java.util.List;

public interface OrganizationDAO {
    public Organization insert(Organization organization);

    public Organization getById(int id);

    public Organization getByDepartment(Department department);

    public List<Organization> getAllLazy();

    public void deleteAll();

    public void delete(Organization organization);

    public void changeName(Organization organization, String name);
}
