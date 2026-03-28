package mx.uv.lis.professionalpracticesystem.logic.DataTransferObject;

import java.sql.Time;

public class ScheduleDTO {
    private int idSchedule;
    private String day;
    private Time startTime;
    private Time endTime;
    
    public ScheduleDTO() {
    }

    public int getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}