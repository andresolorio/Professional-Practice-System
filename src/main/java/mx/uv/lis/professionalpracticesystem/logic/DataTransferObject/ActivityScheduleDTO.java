package mx.uv.lis.professionalpracticesystem.logic.DataTransferObject;

import java.sql.Date;

public class ActivityScheduleDTO {
    private int idActivitySchedule;
    private int idProject;
    private String enrollment;
    private Date approvalDate;
    private String scheduleFileBase64;
    
    public ActivityScheduleDTO() {
    }

    public int getIdActivitySchedule() {
        return idActivitySchedule;
    }

    public void setIdActivitySchedule(int idActivitySchedule) {
        this.idActivitySchedule = idActivitySchedule;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getScheduleFileBase64() {
        return scheduleFileBase64;
    }

    public void setScheduleFileBase64(String scheduleFileBase64) {
        this.scheduleFileBase64 = scheduleFileBase64;
    }
}