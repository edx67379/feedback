package ru.omsu.imit.main.rest.mappers;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ru.omsu.imit.main.utils.ErrorCode;


@Provider
public class WrongURLExceptionMapper implements	ExceptionMapper<NotFoundException> {

    @Override
	public Response toResponse(NotFoundException exception) {
		return ru.omsu.imit.main.utils.RestUtils.failureResponse(Status.NOT_FOUND, new ru.omsu.imit.main.exception.MyException(ErrorCode.WRONG_URL));
	}
}