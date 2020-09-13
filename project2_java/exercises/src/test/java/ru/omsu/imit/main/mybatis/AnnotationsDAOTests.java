package ru.omsu.imit.main.mybatis;

import org.junit.Test;
import ru.omsu.imit.main.models.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AnnotationsDAOTests extends BaseDAOTests {
    @Test
    public void testInsertOrganization() {
        Organization organization = new Organization("OrgABCD");
        organizationDAO.insert(organization);
        Organization orgFromDB = organizationDAO.getById(organization.getId());
        checkOrganizationFields(organization, orgFromDB);
    }

    @Test(expected = RuntimeException.class)
    public void testInsertOrganizationWithNullName() {
        Organization organization = new Organization(null);
        organizationDAO.insert(organization);
    }

    @Test
    public void testChangeOrganizationName() {
        Organization organization = new Organization("OrgABCD");
        organizationDAO.insert(organization);
        Organization orgFromDB = organizationDAO.getById(organization.getId());
        checkOrganizationFields(organization, orgFromDB);
        organizationDAO.changeName(organization, "Org123");
        orgFromDB = organizationDAO.getById(organization.getId());
        assertEquals("Org123", orgFromDB.getName());
    }

    @Test(expected = RuntimeException.class)
    public void testChangeOrganizationNameToNull() {
        Organization organization = new Organization("OrgABCD");
        organizationDAO.insert(organization);
        Organization orgFromDB = organizationDAO.getById(organization.getId());
        checkOrganizationFields(organization, orgFromDB);
        organizationDAO.changeName(organization, null);
    }

    @Test
    public void testInsertDepartments() {
        Organization organization = new Organization("OrgABCD");
        organizationDAO.insert(organization);
        List<Department> departments = new ArrayList<>();
        departments.add(new Department("DepABCD", organization));
        departments.add(new Department("DepEFGH", organization));
        for (Department department : departments) {
            departmentDAO.insert(organization, department);
        }
        assertEquals(departments, organization.getDepartmentList());
    }

    @Test
    public void testGetDepartmentById() {
        Organization organization = new Organization("OrgABCD");
        organizationDAO.insert(organization);
        Department department = new Department("DepAAAA", organization);
        departmentDAO.insert(organization, department);
        Department depFromDB = departmentDAO.getById(department.getId());
        checkDepartmentFields(department, depFromDB);
    }

    @Test
    public void testGetDepartmentByOrganization() {
        Organization organization = new Organization("OrgABCD");
        organizationDAO.insert(organization);
        Department department = new Department("DepAAAA", organization);
        departmentDAO.insert(organization, department);
        List<Department> depFromDB = departmentDAO.getByOrganization(organization);
        checkDepartmentFields(department, depFromDB.get(0));
    }

    @Test
    public void testGetDepartmentByName() {
        Organization organization = new Organization("OrgABCD");
        organizationDAO.insert(organization);
        Department department = new Department("DepAAAA", organization);
        departmentDAO.insert(organization, department);
        List<Department> depFromDB = departmentDAO.getByName("DepAAAA");
        checkDepartmentFields(department, depFromDB.get(0));
    }

    @Test
    public void testChangeDepartmentName() {
        Organization organization = new Organization("OrgABCD");
        organizationDAO.insert(organization);
        Department department = new Department("DepAAAA", organization);
        departmentDAO.insert(organization, department);
        Department depFromDB = departmentDAO.getById(department.getId());
        checkDepartmentFields(department, depFromDB);
        departmentDAO.changeName(department, "DepBBBB");
        depFromDB = departmentDAO.getById(department.getId());
        assertEquals("DepBBBB", depFromDB.getName());
    }

    @Test
    public void testInsertEmployees() {
        Organization organization = new Organization("OrgABCD");
        organizationDAO.insert(organization);
        Department department = new Department("DepABCD", organization);
        departmentDAO.insert(organization, department);
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("EmpA", department));
        employees.add(new Employee("EmpB", department));
        employees.add(new Employee("EmpC", department));
        for (Employee employee : employees) {
            employeeDAO.insert(department, employee);
        }
        assertEquals(employees, department.getEmployeeList());
    }

    @Test
    public void testGetEmployeeById() {
        Organization organization = new Organization("OrgABCD");
        organizationDAO.insert(organization);
        Department department = new Department("DepAAAA", organization);
        departmentDAO.insert(organization, department);
        Employee employee = new Employee("EmpAAAA", department);
        employeeDAO.insert(department, employee);
        Employee empFromDB = employeeDAO.getById(employee.getId());
        checkEmployeeFields(employee, empFromDB);
    }

    @Test
    public void testGetEmployeeByDepartment() {
        Organization organization = new Organization("OrgABCD");
        organizationDAO.insert(organization);
        Department department = new Department("DepAAAA", organization);
        departmentDAO.insert(organization, department);
        Employee employee = new Employee("EmpAAAA", department);
        employeeDAO.insert(department, employee);
        List<Employee> empFromDB = employeeDAO.getByDepartment(department);
        checkEmployeeFields(employee, empFromDB.get(0));
    }

    @Test
    public void testGetEmployeeByName() {
        Organization organization = new Organization("OrgABCD");
        organizationDAO.insert(organization);
        Department department = new Department("DepAAAA", organization);
        departmentDAO.insert(organization, department);
        Employee employee = new Employee("EmpAAAA", department);
        employeeDAO.insert(department, employee);
        List<Employee> empFromDB = employeeDAO.getByName("EmpAAAA");
        checkEmployeeFields(employee, empFromDB.get(0));
    }

    @Test
    public void testChangeEmployeeName() {
        Organization organization = new Organization("OrgABCD");
        organizationDAO.insert(organization);
        Department department = new Department("DepAAAA", organization);
        departmentDAO.insert(organization, department);
        Employee employee = new Employee("EmpAAAA", department);
        employeeDAO.insert(department, employee);

        Employee emlFromDB = employeeDAO.getById(employee.getId());
        checkEmployeeFields(employee, emlFromDB);
        employeeDAO.changeName(employee, "EmpBBBB");
        emlFromDB = employeeDAO.getById(employee.getId());
        assertEquals("EmpBBBB", emlFromDB.getName());
    }

    @Test
    public void testInsertSchedules() {
        Organization organization = new Organization("OrgABCD");
        organizationDAO.insert(organization);
        Department department = new Department("DepABCD", organization);
        departmentDAO.insert(organization, department);
        Employee employee = new Employee("EmpABCD", department);
        employeeDAO.insert(department, employee);
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(new Schedule(LocalTime.of(9,0), LocalTime.of(15, 0), Weekday.Monday));
        schedules.add(new Schedule(LocalTime.of(12,30), LocalTime.of(19, 0), Weekday.Wednesday));
        for (Schedule schedule : schedules) {
            scheduleDAO.insert(employee, schedule);
        }
        assertEquals(schedules, employee.getScheduleList());
    }

    @Test
    public void testGetScheduleById() {
        Organization organization = new Organization("OrgABCD");
        organizationDAO.insert(organization);
        Department department = new Department("DepAAAA", organization);
        departmentDAO.insert(organization, department);
        Employee employee = new Employee("EmpAAAA", department);
        employeeDAO.insert(department, employee);
        Schedule schedule = new Schedule(LocalTime.of(9,0), LocalTime.of(15, 0), Weekday.Monday);
        scheduleDAO.insert(employee, schedule);
        Schedule schFromDB = scheduleDAO.getById(schedule.getId());
        checkScheduleFields(schedule, schFromDB);
    }

    @Test
    public void testGetScheduleByEmployee() {
        Organization organization = new Organization("OrgABCD");
        organizationDAO.insert(organization);
        Department department = new Department("DepAAAA", organization);
        departmentDAO.insert(organization, department);
        Employee employee = new Employee("EmpAAAA", department);
        employeeDAO.insert(department, employee);
        Schedule schedule = new Schedule(LocalTime.of(9,0), LocalTime.of(15, 0), Weekday.Monday);
        scheduleDAO.insert(employee, schedule);
        List<Schedule> schFromDB = scheduleDAO.getByEmployee(employee);
        checkScheduleFields(schedule, schFromDB.get(0));
    }

    @Test
    public void testGetScheduleByTimeStart() {
        Organization organization = new Organization("OrgABCD");
        organizationDAO.insert(organization);
        Department department = new Department("DepAAAA", organization);
        departmentDAO.insert(organization, department);
        Employee employee = new Employee("EmpAAAA", department);
        employeeDAO.insert(department, employee);
        Schedule schedule = new Schedule(LocalTime.of(9,0), LocalTime.of(15, 0), Weekday.Monday);
        scheduleDAO.insert(employee, schedule);
        List<Schedule> schFromDB = scheduleDAO.getByTimeStart(LocalTime.of(9,0));
        checkScheduleFields(schedule, schFromDB.get(0));
    }

    @Test
    public void testGetScheduleByWeekday() {
        Organization organization = new Organization("OrgABCD");
        organizationDAO.insert(organization);
        Department department = new Department("DepAAAA", organization);
        departmentDAO.insert(organization, department);
        Employee employee = new Employee("EmpAAAA", department);
        employeeDAO.insert(department, employee);
        Schedule schedule = new Schedule(LocalTime.of(9,0), LocalTime.of(15, 0), Weekday.Monday);
        scheduleDAO.insert(employee, schedule);
        List<Schedule> schFromDB = scheduleDAO.getByWeekday(Weekday.Monday);
        checkScheduleFields(schedule, schFromDB.get(0));
    }

    @Test
    public void testChangeScheduleTimeEnd() {
        Organization organization = new Organization("OrgABCD");
        organizationDAO.insert(organization);
        Department department = new Department("DepAAAA", organization);
        departmentDAO.insert(organization, department);
        Employee employee = new Employee("EmpAAAA", department);
        employeeDAO.insert(department, employee);
        Schedule schedule = new Schedule(LocalTime.of(9,0), LocalTime.of(15, 0), Weekday.Monday);
        scheduleDAO.insert(employee, schedule);

        Schedule schFromDB = scheduleDAO.getById(schedule.getId());
        checkScheduleFields(schedule, schFromDB);
        scheduleDAO.changeTimeEnd(schedule, LocalTime.of(18, 30));
        schFromDB = scheduleDAO.getById(schedule.getId());
        assertEquals(LocalTime.of(18,30), schFromDB.getTimeEnd());
    }

    @Test
    public void testChangeScheduleWeekday() {
        Organization organization = new Organization("OrgABCD");
        organizationDAO.insert(organization);
        Department department = new Department("DepAAAA", organization);
        departmentDAO.insert(organization, department);
        Employee employee = new Employee("EmpAAAA", department);
        employeeDAO.insert(department, employee);
        Schedule schedule = new Schedule(LocalTime.of(9,0), LocalTime.of(15, 0), Weekday.Monday);
        scheduleDAO.insert(employee, schedule);

        Schedule schFromDB = scheduleDAO.getById(schedule.getId());
        checkScheduleFields(schedule, schFromDB);
        scheduleDAO.changeWeekday(schedule, Weekday.Friday);
        schFromDB = scheduleDAO.getById(schedule.getId());
        assertEquals(Weekday.Friday, schFromDB.getWeekday());
    }
}
