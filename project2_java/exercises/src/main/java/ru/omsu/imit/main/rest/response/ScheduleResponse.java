package ru.omsu.imit.main.rest.response;

public class ScheduleResponse {
    private int idSchedule;
    private String timeStart;
    private String timeEnd;
    private String weekday;


    public ScheduleResponse(int idSchedule, String timeStart, String timeEnd, String weekday) {
        this.idSchedule = idSchedule;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.weekday = weekday;
    }

    protected ScheduleResponse() {
    }

    public int getIdSchedule() {
        return idSchedule;
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
}
