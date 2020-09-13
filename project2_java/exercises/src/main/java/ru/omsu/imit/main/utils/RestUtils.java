package ru.omsu.imit.main.utils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import ru.omsu.imit.main.rest.response.FailureResponse;

public class RestUtils {

	private static final Gson GSON = new Gson();

	public static <T> T getClassInstanceFromJson(Gson gson, String json, Class<T> clazz) throws ru.omsu.imit.main.exception.MyException {
		if (StringUtils.isEmpty(json)) {
			throw new ru.omsu.imit.main.exception.MyException(ErrorCode.NULL_REQUEST);
		}
		try {
			return gson.fromJson(json, clazz);
		} catch (JsonSyntaxException ex) {
			throw new ru.omsu.imit.main.exception.MyException(ErrorCode.JSON_PARSE_EXCEPTION, json);
		}
	}

	public static Response failureResponse(Status status, ru.omsu.imit.main.exception.MyException ex) {
		return Response.status(status).entity(GSON.toJson(new FailureResponse(ex.getErrorCode(), ex.getMessage())))
				.type(MediaType.APPLICATION_JSON).build();
	}

	public static Response failureResponse(ru.omsu.imit.main.exception.MyException ex) {
		return failureResponse(Status.BAD_REQUEST, ex);
	}

}
