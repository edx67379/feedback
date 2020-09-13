package ru.omsu.imit.main.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {
    private int id;
    private String name;
    private List<Department> departmentList;

    public Organization(int id, String name) {
        this.id = id;
        this.name = name;
        departmentList = new ArrayList<>();
    }

    public Organization(String name) {
        this(0, name);
    }

    public Organization() {

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

    public void setName(String name) throws NullPointerException {
        this.name = name;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return id == that.id &&
                name.equals(that.name) &&
                departmentList.equals(that.departmentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, departmentList);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
