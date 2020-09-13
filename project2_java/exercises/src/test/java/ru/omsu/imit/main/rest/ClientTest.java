package ru.omsu.imit.main.rest;

import ru.omsu.imit.main.rest.response.DepartmentResponse;
import ru.omsu.imit.main.rest.response.EmployeeResponse;
import ru.omsu.imit.main.rest.response.OrganizationResponse;
import ru.omsu.imit.main.rest.response.ScheduleResponse;
import ru.omsu.imit.main.utils.ErrorCode;

import org.junit.Test;

public class ClientTest extends BaseClientTest {
	@Test
	public void testInsertOrganization() {
		insertOrganization("OrganizationA", ErrorCode.SUCCESS);
	}

	@Test
	public void testGetOrganizationById() {
		OrganizationResponse insertResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		getOrganizationById(insertResponse.getId(), "OrganizationA", ErrorCode.SUCCESS);
	}

	@Test
	public void testGetOrganizationByWrongId() {
		getOrganizationById(1000, "OrganizationWrong", ErrorCode.ORGANIZATION_NOT_FOUND);
	}

	@Test
	public void testGetAllOrganizations() {
		insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		insertOrganization("OrganizationB", ErrorCode.SUCCESS);
		insertOrganization("OrganizationC", ErrorCode.SUCCESS);
		getAllOrganizations(3, ErrorCode.SUCCESS);
	}

	@Test
	public void testGetAllOrganizationsWhenEmpty() {
		getAllOrganizations(0, ErrorCode.SUCCESS);
	}

	@Test
	public void testEditOrganizationById() {
		OrganizationResponse insertResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		editOrganizationById(insertResponse.getId(), "OrganizationB", ErrorCode.SUCCESS);
	}
	
	@Test
	public void testEditOrganizationByWrongId() {
		editOrganizationById(1000, "OrganizationWrong", ErrorCode.ORGANIZATION_NOT_FOUND);
	}

	@Test
	public void testDeleteOrganizationById() {
		OrganizationResponse insertResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		deleteOrganizationById(insertResponse.getId(), ErrorCode.SUCCESS);
	}

	@Test
	public void testInsertDepartment() {
		OrganizationResponse organizationResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		insertDepartment("DepartmentA", organizationResponse, ErrorCode.SUCCESS);
	}

	@Test
	public void testGetDepartmentById() {
		OrganizationResponse organizationResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		DepartmentResponse insertResponse = insertDepartment("DepartmentA", organizationResponse, ErrorCode.SUCCESS);
		getDepartmentById(insertResponse.getIdDepartment(), "DepartmentA", ErrorCode.SUCCESS);
	}

	@Test
	public void testGetDepartmentByWrongId() {
		getDepartmentById(1000, "DepartmentWrong", ErrorCode.DEPARTMENT_NOT_FOUND);
	}

	@Test
	public void testGetAllDepartments() {
		OrganizationResponse organizationResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		insertDepartment("DepartmentA", organizationResponse, ErrorCode.SUCCESS);
		insertDepartment("DepartmentB", organizationResponse, ErrorCode.SUCCESS);
		insertDepartment("DepartmentC", organizationResponse, ErrorCode.SUCCESS);
		getAllDepartments(3, ErrorCode.SUCCESS);
	}

	@Test
	public void testGetAllDepartmentsWhenEmpty() {
		getAllDepartments(0, ErrorCode.SUCCESS);
	}

	@Test
	public void testEditDepartmentById() {
		OrganizationResponse organizationResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		DepartmentResponse insertResponse = insertDepartment("DepartmentA", organizationResponse, ErrorCode.SUCCESS);
		editDepartmentById(insertResponse.getIdDepartment(),"DepartmentB", organizationResponse, ErrorCode.SUCCESS);
	}

	@Test
	public void testEditDepartmentByWrongId() {
		OrganizationResponse organizationResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		editDepartmentById(1000, "DepartmentWrong", organizationResponse, ErrorCode.DEPARTMENT_NOT_FOUND);
	}

	@Test
	public void testDeleteDepartmentById() {
		OrganizationResponse organizationResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		DepartmentResponse insertResponse = insertDepartment("DepartmentA", organizationResponse, ErrorCode.SUCCESS);
		deleteDepartmentById(insertResponse.getIdDepartment(), ErrorCode.SUCCESS);
	}

	@Test
	public void testInsertEmployee() {
		OrganizationResponse organizationResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		DepartmentResponse departmentResponse = insertDepartment("DepartmentA", organizationResponse, ErrorCode.SUCCESS);
		insertEmployee("EmployeeA", departmentResponse, ErrorCode.SUCCESS);
	}

	@Test
	public void testGetEmployeeById() {
		OrganizationResponse organizationResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		DepartmentResponse departmentResponse = insertDepartment("DepartmentA", organizationResponse, ErrorCode.SUCCESS);
		EmployeeResponse insertResponse = insertEmployee("EmployeeA", departmentResponse, ErrorCode.SUCCESS);
		getEmployeeById(insertResponse.getIdEmployee(), "EmployeeA", ErrorCode.SUCCESS);
	}

	@Test
	public void testGetEmployeeByWrongId() {
		getEmployeeById(1000, "EmployeeWrong", ErrorCode.EMPLOYEE_NOT_FOUND);
	}

	@Test
	public void testGetAllEmployees() {
		OrganizationResponse organizationResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		DepartmentResponse departmentResponse = insertDepartment("DepartmentA", organizationResponse, ErrorCode.SUCCESS);
		insertEmployee("EmployeeA", departmentResponse, ErrorCode.SUCCESS);
		insertEmployee("EmployeeB", departmentResponse, ErrorCode.SUCCESS);
		insertEmployee("EmployeeC", departmentResponse, ErrorCode.SUCCESS);
		getAllEmployees(3, ErrorCode.SUCCESS);
	}

	@Test
	public void testGetAllEmployeesWhenEmpty() {
		getAllEmployees(0, ErrorCode.SUCCESS);
	}

	@Test
	public void testEditEmployeeById() {
		OrganizationResponse organizationResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		DepartmentResponse departmentResponse = insertDepartment("DepartmentA", organizationResponse, ErrorCode.SUCCESS);
		EmployeeResponse insertResponse = insertEmployee("EmployeeA", departmentResponse, ErrorCode.SUCCESS);
		editEmployeeById(insertResponse.getIdEmployee(),"EmployeeB", departmentResponse, ErrorCode.SUCCESS);
	}
	@Test
	public void testDeleteEmployeeById() {
		OrganizationResponse organizationResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		DepartmentResponse departmentResponse = insertDepartment("DepartmentA", organizationResponse, ErrorCode.SUCCESS);
		EmployeeResponse insertResponse = insertEmployee("EmployeeA", departmentResponse, ErrorCode.SUCCESS);
		deleteEmployeeById(insertResponse.getIdEmployee(), ErrorCode.SUCCESS);
	}

	@Test
	public void testInsertSchedule() {
		OrganizationResponse organizationResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		DepartmentResponse departmentResponse = insertDepartment("DepartmentA", organizationResponse, ErrorCode.SUCCESS);
		EmployeeResponse employeeResponse = insertEmployee("EmployeeA", departmentResponse, ErrorCode.SUCCESS);
		insertSchedule("09:00:00", "18:00:00", "Monday", employeeResponse.getIdEmployee(), ErrorCode.SUCCESS);
	}

	@Test
	public void testInsertWrongSchedule1() {
		OrganizationResponse organizationResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		DepartmentResponse departmentResponse = insertDepartment("DepartmentA", organizationResponse, ErrorCode.SUCCESS);
		EmployeeResponse employeeResponse = insertEmployee("EmployeeA", departmentResponse, ErrorCode.SUCCESS);
		insertSchedule("abc", "18:00:00", "Monday", employeeResponse.getIdEmployee(), ErrorCode.EMPTY_PARAM);
	}

	@Test
	public void testInsertWrongSchedule2() {
		OrganizationResponse organizationResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		DepartmentResponse departmentResponse = insertDepartment("DepartmentA", organizationResponse, ErrorCode.SUCCESS);
		EmployeeResponse employeeResponse = insertEmployee("EmployeeA", departmentResponse, ErrorCode.SUCCESS);
		insertSchedule("09:00:00", "18:00:00", "Errrr", employeeResponse.getIdEmployee(), ErrorCode.EMPTY_PARAM);
	}

	@Test
	public void testInsertWrongSchedule3() {
		OrganizationResponse organizationResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		DepartmentResponse departmentResponse = insertDepartment("DepartmentA", organizationResponse, ErrorCode.SUCCESS);
		EmployeeResponse employeeResponse = insertEmployee("EmployeeA", departmentResponse, ErrorCode.SUCCESS);
		insertSchedule("09:00:00", "66:66:66", "Wednesday", employeeResponse.getIdEmployee(), ErrorCode.EMPTY_PARAM);
	}

	@Test
	public void testGetScheduleById() {
		OrganizationResponse organizationResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		DepartmentResponse departmentResponse = insertDepartment("DepartmentA", organizationResponse, ErrorCode.SUCCESS);
		EmployeeResponse employeeResponse = insertEmployee("EmployeeA", departmentResponse, ErrorCode.SUCCESS);
		ScheduleResponse insertResponse = insertSchedule("09:00:00", "18:00:00", "Monday", employeeResponse.getIdEmployee(), ErrorCode.SUCCESS);
		getScheduleById(insertResponse.getIdSchedule(), "09:00:00", "18:00:00", "Monday", ErrorCode.SUCCESS);
	}

	@Test
	public void testGetScheduleByWrongId() {
		getScheduleById(10000, "09:00:00", "18:00:00", "Monday", ErrorCode.SCHEDULE_NOT_FOUND);
	}

	@Test
	public void testGetAllSchedules() {
		OrganizationResponse organizationResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		DepartmentResponse departmentResponse = insertDepartment("DepartmentA", organizationResponse, ErrorCode.SUCCESS);
		EmployeeResponse employeeResponse = insertEmployee("EmployeeA", departmentResponse, ErrorCode.SUCCESS);
		insertSchedule("09:00:00", "18:00:00", "Monday", employeeResponse.getIdEmployee(), ErrorCode.SUCCESS);
		insertSchedule("10:00:00", "18:30:00", "Wednesday", employeeResponse.getIdEmployee(), ErrorCode.SUCCESS);
		insertSchedule("12:00:00", "22:20:00", "Friday", employeeResponse.getIdEmployee(), ErrorCode.SUCCESS);
		getAllSchedules(3, ErrorCode.SUCCESS);
	}

	@Test
	public void testGetAllSchedulesWhenEmpty() {
		getAllSchedules(0, ErrorCode.SUCCESS);
	}

	@Test
	public void testEditScheduleById() {
		OrganizationResponse organizationResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		DepartmentResponse departmentResponse = insertDepartment("DepartmentA", organizationResponse, ErrorCode.SUCCESS);
		EmployeeResponse employeeResponse = insertEmployee("EmployeeA", departmentResponse, ErrorCode.SUCCESS);
		ScheduleResponse insertResponse = insertSchedule("09:00:00", "18:00:00", "Monday", employeeResponse.getIdEmployee(), ErrorCode.SUCCESS);
		editScheduleById(insertResponse.getIdSchedule(),"08:30:00", "16:30:00", "Wednesday", employeeResponse.getIdEmployee(), ErrorCode.SUCCESS);
	}
	@Test
	public void testDeleteSchedulesById() {
		OrganizationResponse organizationResponse = insertOrganization("OrganizationA", ErrorCode.SUCCESS);
		DepartmentResponse departmentResponse = insertDepartment("DepartmentA", organizationResponse, ErrorCode.SUCCESS);
		EmployeeResponse employeeResponse = insertEmployee("EmployeeA", departmentResponse, ErrorCode.SUCCESS);
		ScheduleResponse insertResponse = insertSchedule("09:00:00", "18:00:00", "Monday", employeeResponse.getIdEmployee(), ErrorCode.SUCCESS);
		deleteScheduleById(insertResponse.getIdSchedule(), ErrorCode.SUCCESS);
	}
}