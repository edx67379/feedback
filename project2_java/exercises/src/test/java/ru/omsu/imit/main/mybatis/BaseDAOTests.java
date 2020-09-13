package ru.omsu.imit.main.mybatis;

import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import ru.omsu.imit.main.dao.*;
import ru.omsu.imit.main.daoImpl.*;
import ru.omsu.imit.main.models.Department;
import ru.omsu.imit.main.models.Employee;
import ru.omsu.imit.main.models.Organization;
import ru.omsu.imit.main.models.Schedule;
import ru.omsu.imit.main.utils.MyBatisUtils;

import static org.junit.Assert.assertEquals;

public class BaseDAOTests {
    protected CommonDAO commonDAO = new CommonDAOImpl();
    protected OrganizationDAO organizationDAO = new OrganizationDAOImpl();
    protected DepartmentDAO departmentDAO = new DepartmentDAOImpl();
    protected EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    protected ScheduleDAO scheduleDAO = new ScheduleDAOImpl();


    @BeforeClass()
    public static void init() {
        Assume.assumeTrue(MyBatisUtils.initSqlSessionFactory());
    }

    @Before()
    public void clearDatabase() {
        commonDAO.clear();
    }

    protected void checkOrganizationFields(Organization organization1, Organization organization2) {
        assertEquals(organization1.getId(), organization2.getId());
        assertEquals(organization1.getName(), organization2.getName());
    }

    protected void checkDepartmentFields(Department department1, Department department2) {
        assertEquals(department1.getId(), department2.getId());
        assertEquals(department1.getName(), department2.getName());
        checkOrganizationFields(department1.getOrganization(), department2.getOrganization());
    }

    protected void checkEmployeeFields(Employee employee1, Employee employee2) {
        assertEquals(employee1.getId(), employee2.getId());
        assertEquals(employee1.getName(), employee2.getName());
        checkDepartmentFields(employee1.getDepartment(), employee2.getDepartment());
    }

    protected void checkScheduleFields(Schedule schedule1, Schedule schedule2) {
        assertEquals(schedule1.getId(), schedule2.getId());
        assertEquals(schedule1.getTimeStart(), schedule2.getTimeStart());
        assertEquals(schedule1.getTimeEnd(), schedule2.getTimeEnd());
        assertEquals(schedule1.getWeekday(), schedule2.getWeekday());
    }
}
