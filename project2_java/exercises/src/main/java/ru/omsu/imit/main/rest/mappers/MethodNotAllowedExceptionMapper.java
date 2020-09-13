package ru.omsu.imit.main.rest.mappers;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ru.omsu.imit.main.utils.ErrorCode;


@Provider
public class MethodNotAllowedExceptionMapper implements	ExceptionMapper<NotAllowedException> {

    @Override
	public Response toResponse(NotAllowedException exception) {
		return ru.omsu.imit.main.utils.RestUtils.failureResponse(Status.METHOD_NOT_ALLOWED, new ru.omsu.imit.main.exception.MyException(ErrorCode.METHOD_NOT_ALLOWED));
	}
}