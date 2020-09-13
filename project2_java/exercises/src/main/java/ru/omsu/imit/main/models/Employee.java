package ru.omsu.imit.main.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employee {
    private int id;
    private String name;
    private List<Schedule> scheduleList;
    private Department department;

    public Employee(int id, String name, Department department) {
        this.id = id;
        this.name = name;
        this.department = department;
        scheduleList = new ArrayList<>();
    }

    public Employee(String name, Department department) {
        this(0, name, department);
    }

    public Employee() {

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

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                Objects.equals(name, employee.name) &&
                Objects.equals(scheduleList, employee.scheduleList) &&
                Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, scheduleList, department);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                '}';
    }
}

