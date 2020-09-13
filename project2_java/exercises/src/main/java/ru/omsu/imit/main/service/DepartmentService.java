package ru.omsu.imit.main.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.main.dao.DepartmentDAO;
import ru.omsu.imit.main.daoImpl.DepartmentDAOImpl;
import ru.omsu.imit.main.exception.MyException;
import ru.omsu.imit.main.models.Department;
import ru.omsu.imit.main.models.Organization;
import ru.omsu.imit.main.rest.request.DepartmentRequest;
import ru.omsu.imit.main.rest.request.RequestValidator;
import ru.omsu.imit.main.rest.response.DepartmentResponse;
import ru.omsu.imit.main.rest.response.EmptySuccessResponse;
import ru.omsu.imit.main.utils.ErrorCode;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ru.omsu.imit.main.service.DepartmentService.class);
    private static final Gson GSON = new GsonBuilder().create();
    private DepartmentDAO departmentDAO = new DepartmentDAOImpl();

    public Response insertDepartment(String json) {
        LOGGER.debug("Insert department " + json);
        try {
            DepartmentRequest request = ru.omsu.imit.main.utils.RestUtils.getClassInstanceFromJson(GSON, json, DepartmentRequest.class);
            RequestValidator.departmentRequestValidate(request);
            Organization organization = new Organization(request.getIdOrganization(), request.getNameOrganization());
            Department department = new Department(request.getNameDepartment(), organization);
            Department insertedDepartment = departmentDAO.insert(organization, department);
            String response = GSON.toJson(new DepartmentResponse(insertedDepartment.getId(), insertedDepartment.getName(), organization.getId(), organization.getName()));
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ru.omsu.imit.main.exception.MyException ex) {
            return ru.omsu.imit.main.utils.RestUtils.failureResponse(ex);
        }

    }

    public Response getById(int id) {
        LOGGER.debug("Get department by id " + id);
        try {
            Department department = departmentDAO.getById(id);
            if(department == null) {
                throw new MyException(ErrorCode.DEPARTMENT_NOT_FOUND);
            }
            String response = GSON.toJson(new ru.omsu.imit.main.rest.response.DepartmentResponse(department.getId(), department.getName(),
                    department.getOrganization().getId(), department.getOrganization().getName()));
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ru.omsu.imit.main.exception.MyException ex) {
            return ru.omsu.imit.main.utils.RestUtils.failureResponse(ex);
        }
    }

    public Response getAll() {
        LOGGER.debug("Get all departments");
        List<Department> departmentList = departmentDAO.getAllLazy();
        List<ru.omsu.imit.main.rest.response.DepartmentResponse> responseList = new ArrayList<>();
        for (Department department : departmentList)
            responseList.add(new ru.omsu.imit.main.rest.response.DepartmentResponse(department.getId(), department.getName(),
                    department.getOrganization().getId(), department.getOrganization().getName()));
        String response = GSON.toJson(responseList);
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }

    public Response editById(int id, String json) {
        LOGGER.debug("Edit department by id " + id);
        try {
            ru.omsu.imit.main.rest.request.DepartmentRequest request = ru.omsu.imit.main.utils.RestUtils.getClassInstanceFromJson(GSON, json, ru.omsu.imit.main.rest.request.DepartmentRequest.class);
            Department department = departmentDAO.getById(id);
            if(department == null) {
                throw new MyException(ErrorCode.DEPARTMENT_NOT_FOUND);
            }
            departmentDAO.changeName(department, request.getNameDepartment());
            String response = GSON.toJson(new EmptySuccessResponse());
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ru.omsu.imit.main.exception.MyException ex) {
            return ru.omsu.imit.main.utils.RestUtils.failureResponse(ex);
        }
    }

    public Response deleteById(int id) {
        LOGGER.debug("Delete department by id " + id);
        try {
            Department department = departmentDAO.getById(id);
            if(department == null) {
                throw new MyException(ErrorCode.DEPARTMENT_NOT_FOUND);
            }
            departmentDAO.delete(department);
            String response = GSON.toJson(new EmptySuccessResponse());
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ru.omsu.imit.main.exception.MyException ex) {
            return ru.omsu.imit.main.utils.RestUtils.failureResponse(ex);
        }
    }
}
