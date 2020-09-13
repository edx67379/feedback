package ru.omsu.imit.main.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department {
    private int id;
    private String name;
    private List<Employee> employeeList;
    private Organization organization;

    public Department(int id, String name, Organization organization) {
        this.id = id;
        this.name = name;
        this.organization = organization;
        employeeList = new ArrayList<>();
    }

    public Department(String name, Organization organization) {
        this(0, name, organization);
    }

    public Department() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(employeeList, that.employeeList) &&
                Objects.equals(organization, that.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, employeeList, organization);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", organization=" + organization +
                '}';
    }
}
