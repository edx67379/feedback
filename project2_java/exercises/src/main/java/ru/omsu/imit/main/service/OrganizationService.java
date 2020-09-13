package ru.omsu.imit.main.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ru.omsu.imit.main.dao.OrganizationDAO;
import ru.omsu.imit.main.daoImpl.OrganizationDAOImpl;
import ru.omsu.imit.main.exception.MyException;
import ru.omsu.imit.main.models.Organization;
import ru.omsu.imit.main.rest.request.OrganizationRequest;
import ru.omsu.imit.main.rest.request.RequestValidator;
import ru.omsu.imit.main.rest.response.EmptySuccessResponse;
import ru.omsu.imit.main.rest.response.OrganizationResponse;
import ru.omsu.imit.main.utils.ErrorCode;


public class OrganizationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ru.omsu.imit.main.service.OrganizationService.class);
	private static final Gson GSON = new GsonBuilder().create();
	private OrganizationDAO organizationDAO = new OrganizationDAOImpl();

	public Response insertOrganization(String json) {
		LOGGER.debug("Insert organization " + json);
		try {
			OrganizationRequest request = ru.omsu.imit.main.utils.RestUtils.getClassInstanceFromJson(GSON, json, OrganizationRequest.class);
			RequestValidator.organizationRequestValidate(request);
			Organization organization = new Organization(request.getName());
			Organization insertedOrganization = organizationDAO.insert(organization);
			String response = GSON.toJson(new OrganizationResponse(insertedOrganization.getId(), insertedOrganization.getName()));
			return Response.ok(response, MediaType.APPLICATION_JSON).build();
		} catch (ru.omsu.imit.main.exception.MyException ex) {
			return ru.omsu.imit.main.utils.RestUtils.failureResponse(ex);
		}

	}

	public Response getById(int id) {
		LOGGER.debug("Get organization by id " + id);
		try {
			Organization organization = organizationDAO.getById(id);
			if(organization == null) {
				throw new MyException(ErrorCode.ORGANIZATION_NOT_FOUND);
			}
			String response = GSON.toJson(new ru.omsu.imit.main.rest.response.OrganizationResponse(organization.getId(), organization.getName()));
			return Response.ok(response, MediaType.APPLICATION_JSON).build();
		} catch (ru.omsu.imit.main.exception.MyException ex) {
			return ru.omsu.imit.main.utils.RestUtils.failureResponse(ex);
		}
	}

	public Response getAll() {
		LOGGER.debug("Get all organizations");
		List<Organization> organizationList = organizationDAO.getAllLazy();
		List<ru.omsu.imit.main.rest.response.OrganizationResponse> responseList = new ArrayList<>();
		for (Organization organization : organizationList)
			responseList.add(new ru.omsu.imit.main.rest.response.OrganizationResponse(organization.getId(), organization.getName()));
		String response = GSON.toJson(responseList);
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}

	public Response editById(int id, String json) {
		LOGGER.debug("Edit organization by id " + id);
		try {
			ru.omsu.imit.main.rest.request.OrganizationRequest request = ru.omsu.imit.main.utils.RestUtils.getClassInstanceFromJson(GSON, json, ru.omsu.imit.main.rest.request.OrganizationRequest.class);
			Organization organization = organizationDAO.getById(id);
			if(organization == null) {
				throw new MyException(ErrorCode.ORGANIZATION_NOT_FOUND);
			}
			organizationDAO.changeName(organization, request.getName());
			String response = GSON.toJson(new EmptySuccessResponse());
			return Response.ok(response, MediaType.APPLICATION_JSON).build();
		} catch (ru.omsu.imit.main.exception.MyException ex) {
			return ru.omsu.imit.main.utils.RestUtils.failureResponse(ex);
		}
	}

	public Response deleteById(int id) {
		LOGGER.debug("Delete organization by id " + id);
		try {
			Organization organization = organizationDAO.getById(id);
			if(organization == null) {
				throw new MyException(ErrorCode.ORGANIZATION_NOT_FOUND);
			}
			organizationDAO.delete(organization);
			String response = GSON.toJson(new EmptySuccessResponse());
			return Response.ok(response, MediaType.APPLICATION_JSON).build();
		} catch (ru.omsu.imit.main.exception.MyException ex) {
			return ru.omsu.imit.main.utils.RestUtils.failureResponse(ex);
		}
	}

}
