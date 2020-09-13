package ru.omsu.imit.main.rest;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.omsu.imit.main.client.MyClient;
import ru.omsu.imit.main.dao.*;
import ru.omsu.imit.main.daoImpl.*;
import ru.omsu.imit.main.rest.request.DepartmentRequest;
import ru.omsu.imit.main.rest.request.EmployeeRequest;
import ru.omsu.imit.main.rest.request.OrganizationRequest;
import ru.omsu.imit.main.rest.request.ScheduleRequest;
import ru.omsu.imit.main.rest.response.*;
import ru.omsu.imit.main.server.MyServer;
import ru.omsu.imit.main.server.config.Settings;
import ru.omsu.imit.main.utils.ErrorCode;
import ru.omsu.imit.main.utils.MyBatisUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class BaseClientTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseClientTest.class);

	protected static MyClient client = new MyClient();
	private static String baseURL;
    private CommonDAO commonDAO = new CommonDAOImpl();
    protected OrganizationDAO organizationDAO = new OrganizationDAOImpl();
    protected DepartmentDAO departmentDAO = new DepartmentDAOImpl();
    protected EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    protected ScheduleDAO scheduleDAO = new ScheduleDAOImpl();

	private static void initialize() {
		String hostName = null;
		try {
			hostName = InetAddress.getLocalHost().getCanonicalHostName();
		} catch (UnknownHostException e) {
			LOGGER.debug("Can't determine my own host name", e);
		}
		baseURL = "http://" + hostName + ":" + Settings.getRestHTTPPort() + "/api";
	}

    @BeforeClass()
    public static void init() {
        Assume.assumeTrue(MyBatisUtils.initSqlSessionFactory());
    }

	@BeforeClass
	public static void startServer() {
		initialize();
		MyServer.createServer();
	}

	@AfterClass
	public static void stopServer() {
		MyServer.stopServer();
	}

	@Before
	public void clearDataBase() {
		commonDAO.clear();
	}
	
	public static String getBaseURL() {
		return baseURL;
	}

	protected void checkFailureResponse(Object response, ErrorCode expectedStatus) {
		assertTrue(response instanceof FailureResponse);
		FailureResponse failureResponseObject = (FailureResponse) response;
		assertEquals(expectedStatus, failureResponseObject.getErrorCode());
	}
	
	protected OrganizationResponse insertOrganization(String name, ErrorCode expectedStatus) {
		OrganizationRequest request = new OrganizationRequest(name);
		Object response = client.post(baseURL + "/organizations", request, OrganizationResponse.class);
		if (response instanceof OrganizationResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			OrganizationResponse insertOrganizationResponse = (OrganizationResponse) response;
			assertEquals(name, insertOrganizationResponse.getName());
			return insertOrganizationResponse;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	protected OrganizationResponse getOrganizationById(int id, String expectedName, ErrorCode expectedStatus) {
		Object response = client.get(baseURL + "/organizations/" + id, OrganizationResponse.class);
		if (response instanceof OrganizationResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			OrganizationResponse getOrganizationByIdResponse = (OrganizationResponse) response;
			assertEquals(id, getOrganizationByIdResponse.getId());
			assertEquals(expectedName, getOrganizationByIdResponse.getName());
			return getOrganizationByIdResponse;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	protected List<OrganizationResponse> getAllOrganizations(int expeectedCount, ErrorCode expectedStatus) {
		Object response = client.get(baseURL + "/organizations/", List.class);
		if (response instanceof List<?>) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			@SuppressWarnings("unchecked")
			List<OrganizationResponse> responseList = (List<OrganizationResponse>) response;
			assertEquals(expeectedCount, responseList.size());
			return responseList;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	
	protected EmptySuccessResponse editOrganizationById(int id, String name, ErrorCode expectedStatus) {
		OrganizationRequest request = new OrganizationRequest(name);
		Object response = client.put(baseURL + "/organizations/" + id , request, EmptySuccessResponse.class);
		if (response instanceof EmptySuccessResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			return (EmptySuccessResponse)response;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}
	
	protected EmptySuccessResponse deleteOrganizationById(int id, ErrorCode expectedStatus) {
		Object response = client.delete(baseURL + "/organizations/" + id , EmptySuccessResponse.class);
		if (response instanceof EmptySuccessResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			return (EmptySuccessResponse)response;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

    protected DepartmentResponse insertDepartment(String name, OrganizationResponse organizationResponse, ErrorCode expectedStatus) {
        DepartmentRequest request = new DepartmentRequest(name, organizationResponse.getId(), organizationResponse.getName());
        Object response = client.post(baseURL + "/departments", request, DepartmentResponse.class);
        if (response instanceof DepartmentResponse) {
            assertEquals(ErrorCode.SUCCESS, expectedStatus);
            DepartmentResponse insertDepartmentResponse = (DepartmentResponse) response;
            assertEquals(name, insertDepartmentResponse.getNameDepartment());
            assertEquals(organizationResponse.getName(), insertDepartmentResponse.getNameOrganization());
            assertEquals(organizationResponse.getId(), insertDepartmentResponse.getIdOrganization());
            return insertDepartmentResponse;
        } else {
            checkFailureResponse(response, expectedStatus);
            return null;
        }
    }

    protected DepartmentResponse getDepartmentById(int id, String expectedName, ErrorCode expectedStatus) {
        Object response = client.get(baseURL + "/departments/" + id, DepartmentResponse.class);
        if (response instanceof DepartmentResponse) {
            assertEquals(ErrorCode.SUCCESS, expectedStatus);
            DepartmentResponse getDepartmentByIdResponse = (DepartmentResponse) response;
            assertEquals(id, getDepartmentByIdResponse.getIdDepartment());
            assertEquals(expectedName, getDepartmentByIdResponse.getNameDepartment());
            return getDepartmentByIdResponse;
        } else {
            checkFailureResponse(response, expectedStatus);
            return null;
        }
    }

    protected List<DepartmentResponse> getAllDepartments(int expeectedCount, ErrorCode expectedStatus) {
        Object response = client.get(baseURL + "/departments/", List.class);
        if (response instanceof List<?>) {
            assertEquals(ErrorCode.SUCCESS, expectedStatus);
            @SuppressWarnings("unchecked")
            List<DepartmentResponse> responseList = (List<DepartmentResponse>) response;
            assertEquals(expeectedCount, responseList.size());
            return responseList;
        } else {
            checkFailureResponse(response, expectedStatus);
            return null;
        }
    }


    protected EmptySuccessResponse editDepartmentById(int id, String name, OrganizationResponse organizationResponse, ErrorCode expectedStatus) {
        DepartmentRequest request = new DepartmentRequest(name, organizationResponse.getId(), organizationResponse.getName());
        Object response = client.put(baseURL + "/departments/" + id , request, EmptySuccessResponse.class);
        if (response instanceof EmptySuccessResponse) {
            assertEquals(ErrorCode.SUCCESS, expectedStatus);
            return (EmptySuccessResponse)response;
        } else {
            checkFailureResponse(response, expectedStatus);
            return null;
        }
    }

    protected EmptySuccessResponse deleteDepartmentById(int id, ErrorCode expectedStatus) {
        Object response = client.delete(baseURL + "/departments/" + id , EmptySuccessResponse.class);
        if (response instanceof EmptySuccessResponse) {
            assertEquals(ErrorCode.SUCCESS, expectedStatus);
            return (EmptySuccessResponse)response;
        } else {
            checkFailureResponse(response, expectedStatus);
            return null;
        }
    }

	protected EmployeeResponse insertEmployee(String name, DepartmentResponse departmentResponse, ErrorCode expectedStatus) {
		EmployeeRequest request = new EmployeeRequest(name, departmentResponse.getIdDepartment(), departmentResponse.getNameDepartment(),
				departmentResponse.getIdOrganization(), departmentResponse.getNameOrganization());
		Object response = client.post(baseURL + "/employees", request, EmployeeResponse.class);
		if (response instanceof EmployeeResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			EmployeeResponse insertEmployeeResponse = (EmployeeResponse) response;
			assertEquals(name, insertEmployeeResponse.getNameEmployee());
			assertEquals(departmentResponse.getNameDepartment(), insertEmployeeResponse.getNameDepartment());
			assertEquals(departmentResponse.getIdDepartment(), insertEmployeeResponse.getIdDepartment());
			assertEquals(departmentResponse.getNameOrganization(), insertEmployeeResponse.getNameOrganization());
			assertEquals(departmentResponse.getIdOrganization(), insertEmployeeResponse.getIdOrganization());
			return insertEmployeeResponse;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	protected EmployeeResponse getEmployeeById(int id, String expectedName, ErrorCode expectedStatus) {
		Object response = client.get(baseURL + "/employees/" + id, EmployeeResponse.class);
		if (response instanceof EmployeeResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			EmployeeResponse getEmployeeByIdResponse = (EmployeeResponse) response;
			assertEquals(id, getEmployeeByIdResponse.getIdEmployee());
			assertEquals(expectedName, getEmployeeByIdResponse.getNameEmployee());
			return getEmployeeByIdResponse;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	protected List<EmployeeResponse> getAllEmployees(int expeectedCount, ErrorCode expectedStatus) {
		Object response = client.get(baseURL + "/employees/", List.class);
		if (response instanceof List<?>) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			@SuppressWarnings("unchecked")
			List<EmployeeResponse> responseList = (List<EmployeeResponse>) response;
			assertEquals(expeectedCount, responseList.size());
			return responseList;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	protected EmptySuccessResponse editEmployeeById(int id, String name, DepartmentResponse departmentResponse, ErrorCode expectedStatus) {
		EmployeeRequest request = new EmployeeRequest(name, departmentResponse.getIdDepartment(), departmentResponse.getNameDepartment(),
				departmentResponse.getIdOrganization(), departmentResponse.getNameOrganization());
		Object response = client.put(baseURL + "/employees/" + id , request, EmptySuccessResponse.class);
		if (response instanceof EmptySuccessResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			return (EmptySuccessResponse)response;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	protected EmptySuccessResponse deleteEmployeeById(int id, ErrorCode expectedStatus) {
		Object response = client.delete(baseURL + "/employees/" + id , EmptySuccessResponse.class);
		if (response instanceof EmptySuccessResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			return (EmptySuccessResponse)response;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	protected ScheduleResponse insertSchedule(String timeStart, String timeEnd, String weekday, int employeeId, ErrorCode expectedStatus) {
		ScheduleRequest request = new ScheduleRequest(timeStart, timeEnd, weekday, employeeId);
		Object response = client.post(baseURL + "/schedules", request, ScheduleResponse.class);
		if (response instanceof ScheduleResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			ScheduleResponse insertScheduleResponse = (ScheduleResponse) response;
			assertEquals(timeStart, insertScheduleResponse.getTimeStart());
			assertEquals(timeEnd, insertScheduleResponse.getTimeEnd());
			assertEquals(weekday, insertScheduleResponse.getWeekday());
			return insertScheduleResponse;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	protected ScheduleResponse getScheduleById(int id, String timeStart, String timeEnd, String weekday, ErrorCode expectedStatus) {
		Object response = client.get(baseURL + "/schedules/" + id, ScheduleResponse.class);
		if (response instanceof ScheduleResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			ScheduleResponse getScheduleByIdResponse = (ScheduleResponse) response;
			assertEquals(id, getScheduleByIdResponse.getIdSchedule());
			assertEquals(timeStart, getScheduleByIdResponse.getTimeStart());
			assertEquals(timeEnd, getScheduleByIdResponse.getTimeEnd());
			assertEquals(weekday, getScheduleByIdResponse.getWeekday());
			return getScheduleByIdResponse;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	protected ScheduleResponse getScheduleByWeekday(String weekday, int id, String timeStart, String timeEnd, ErrorCode expectedStatus) {
		Object response = client.get(baseURL + "/schedules/by/" + weekday, ScheduleResponse.class);
		if (response instanceof ScheduleResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			ScheduleResponse getScheduleByIdResponse = (ScheduleResponse) response;
			assertEquals(id, getScheduleByIdResponse.getIdSchedule());
			assertEquals(timeStart, getScheduleByIdResponse.getTimeStart());
			assertEquals(timeEnd, getScheduleByIdResponse.getTimeEnd());
			assertEquals(weekday, getScheduleByIdResponse.getWeekday());
			return getScheduleByIdResponse;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	protected List<ScheduleResponse> getAllSchedules(int expeectedCount, ErrorCode expectedStatus) {
		Object response = client.get(baseURL + "/schedules/", List.class);
		if (response instanceof List<?>) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			@SuppressWarnings("unchecked")
			List<ScheduleResponse> responseList = (List<ScheduleResponse>) response;
			assertEquals(expeectedCount, responseList.size());
			return responseList;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	protected EmptySuccessResponse editScheduleById(int id, String timeStart, String timeEnd, String weekday, int employeeId, ErrorCode expectedStatus) {
		ScheduleRequest request = new ScheduleRequest(timeStart, timeEnd, weekday, employeeId);
		Object response = client.put(baseURL + "/schedules/" + id , request, EmptySuccessResponse.class);
		if (response instanceof EmptySuccessResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			return (EmptySuccessResponse)response;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	protected EmptySuccessResponse deleteScheduleById(int id, ErrorCode expectedStatus) {
		Object response = client.delete(baseURL + "/schedules/" + id , EmptySuccessResponse.class);
		if (response instanceof EmptySuccessResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			return (EmptySuccessResponse)response;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}
}
