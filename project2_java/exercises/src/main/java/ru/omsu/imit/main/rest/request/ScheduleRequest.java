package ru.omsu.imit.main.rest.request;

public class ScheduleRequest {
    private String timeStart;
    private String timeEnd;
    private String weekday;
    private int idEmployee;

    public ScheduleRequest(String timeStart, String timeEnd, String weekday, int idEmployee) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.weekday = weekday;
        this.idEmployee = idEmployee;
    }

    protected ScheduleRequest() {
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public String getWeekday() {
        return weekday;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

}
