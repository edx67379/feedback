package ru.omsu.imit.main.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.main.dao.EmployeeDAO;
import ru.omsu.imit.main.daoImpl.EmployeeDAOImpl;
import ru.omsu.imit.main.exception.MyException;
import ru.omsu.imit.main.models.Department;
import ru.omsu.imit.main.models.Employee;
import ru.omsu.imit.main.models.Organization;
import ru.omsu.imit.main.rest.request.EmployeeRequest;
import ru.omsu.imit.main.rest.request.RequestValidator;
import ru.omsu.imit.main.rest.response.EmployeeResponse;
import ru.omsu.imit.main.rest.response.EmptySuccessResponse;
import ru.omsu.imit.main.utils.ErrorCode;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ru.omsu.imit.main.service.EmployeeService.class);
    private static final Gson GSON = new GsonBuilder().create();
    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    public Response insertEmployee(String json) {
        LOGGER.debug("Insert employee " + json);
        try {
            EmployeeRequest request = ru.omsu.imit.main.utils.RestUtils.getClassInstanceFromJson(GSON, json, EmployeeRequest.class);
            RequestValidator.employeeRequestValidate(request);
            Organization organization = new Organization(request.getIdOrganization(), request.getNameOrganization());
            Department department = new Department(request.getIdDepartment(), request.getNameDepartment(), organization);
            Employee employee = new Employee(request.getNameEmployee(), department);
            Employee insertedEmployee = employeeDAO.insert(department, employee);
            String response = GSON.toJson(new EmployeeResponse(insertedEmployee.getId(), insertedEmployee.getName(),
                    department.getId(), department.getName(), organization.getId(), organization.getName()));
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ru.omsu.imit.main.exception.MyException ex) {
            return ru.omsu.imit.main.utils.RestUtils.failureResponse(ex);
        }

    }

    public Response getById(int id) {
        LOGGER.debug("Get employee by id " + id);
        try {
            Employee employee = employeeDAO.getById(id);
            if(employee == null) {
                throw new MyException(ErrorCode.EMPLOYEE_NOT_FOUND);
            }
            String response = GSON.toJson(new EmployeeResponse(employee.getId(), employee.getName(),
                    employee.getDepartment().getId(), employee.getDepartment().getName(),
                    employee.getDepartment().getOrganization().getId(), employee.getDepartment().getOrganization().getName()));
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ru.omsu.imit.main.exception.MyException ex) {
            return ru.omsu.imit.main.utils.RestUtils.failureResponse(ex);
        }
    }

    public Response getAll() {
        LOGGER.debug("Get all employees");
        List<Employee> employeeList = employeeDAO.getAllLazy();
        List<ru.omsu.imit.main.rest.response.EmployeeResponse> responseList = new ArrayList<>();
        for (Employee employee : employeeList)
            responseList.add(new EmployeeResponse(employee.getId(), employee.getName(),
                    employee.getDepartment().getId(), employee.getDepartment().getName(),
                    employee.getDepartment().getOrganization().getId(), employee.getDepartment().getOrganization().getName()));
        String response = GSON.toJson(responseList);
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }

    public Response editById(int id, String json) {
        LOGGER.debug("Edit employee by id " + id);
        try {
            ru.omsu.imit.main.rest.request.EmployeeRequest request = ru.omsu.imit.main.utils.RestUtils.getClassInstanceFromJson(GSON, json, ru.omsu.imit.main.rest.request.EmployeeRequest.class);
            Employee employee = employeeDAO.getById(id);
            if(employee == null) {
                throw new MyException(ErrorCode.EMPLOYEE_NOT_FOUND);
            }
            employeeDAO.changeName(employee, request.getNameEmployee());
            String response = GSON.toJson(new EmptySuccessResponse());
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ru.omsu.imit.main.exception.MyException ex) {
            return ru.omsu.imit.main.utils.RestUtils.failureResponse(ex);
        }
    }

    public Response deleteById(int id) {
        LOGGER.debug("Delete employee by id " + id);
        try {
            Employee employee = employeeDAO.getById(id);
            if(employee == null) {
                throw new MyException(ErrorCode.EMPLOYEE_NOT_FOUND);
            }
            employeeDAO.delete(employee);
            String response = GSON.toJson(new EmptySuccessResponse());
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ru.omsu.imit.main.exception.MyException ex) {
            return ru.omsu.imit.main.utils.RestUtils.failureResponse(ex);
        }
    }
}
