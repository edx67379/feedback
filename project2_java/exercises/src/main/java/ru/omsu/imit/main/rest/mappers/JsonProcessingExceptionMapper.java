package ru.omsu.imit.main.rest.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.omsu.imit.main.exception.MyException;
import ru.omsu.imit.main.utils.ErrorCode;
import ru.omsu.imit.main.utils.RestUtils;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonProcessingExceptionMapper implements ExceptionMapper<JsonProcessingException> {
    @Override
    public Response toResponse(JsonProcessingException exception) {
        return RestUtils.failureResponse(Response.Status.BAD_REQUEST, new MyException(ErrorCode.JSON_PARSE_EXCEPTION));
    }
}