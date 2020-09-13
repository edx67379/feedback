package ru.omsu.imit.main.rest.request;

import org.apache.commons.validator.routines.TimeValidator;
import ru.omsu.imit.main.exception.MyException;
import ru.omsu.imit.main.models.Weekday;
import ru.omsu.imit.main.utils.ErrorCode;

public class RequestValidator {
    public static void organizationRequestValidate(OrganizationRequest request) {
        if(request.getName() == null || "".equals(request.getName())) {
            throw new MyException(ErrorCode.EMPTY_PARAM);
        }
    }

    public static void departmentRequestValidate(DepartmentRequest request) {
        if(request.getNameDepartment() == null || "".equals(request.getNameDepartment())
                || request.getNameOrganization() == null || "".equals(request.getNameOrganization())) {
            throw new MyException(ErrorCode.EMPTY_PARAM);
        }
    }

    public static void employeeRequestValidate(EmployeeRequest request) {
        if(request.getNameEmployee() == null || "".equals(request.getNameEmployee())
                || request.getNameDepartment() == null || "".equals(request.getNameDepartment())
                || request.getNameOrganization() == null || "".equals(request.getNameOrganization())) {
            throw new MyException(ErrorCode.EMPTY_PARAM);
        }
    }

    public static void scheduleRequestValidate(ScheduleRequest request) {
        if(request.getTimeStart() == null || request.getTimeEnd() == null || request.getWeekday() == null
                || !Weekday.weekdays().contains(request.getWeekday())
                || !request.getTimeStart().matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$")
                || !request.getTimeEnd().matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$")){
            throw new MyException(ErrorCode.EMPTY_PARAM);
        }
    }
}
