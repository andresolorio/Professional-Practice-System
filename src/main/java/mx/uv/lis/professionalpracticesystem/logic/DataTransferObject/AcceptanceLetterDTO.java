package mx.uv.lis.professionalpracticesystem.logic.DataTransferObject;

import java.sql.Date;

public class AcceptanceLetterDTO {
    private int idAcceptanceLetter;
    private String enrollment;
    private Date issueDate;
    private String acceptanceFileBase64;
    
    public AcceptanceLetterDTO() {
    }

    public int getIdAcceptanceLetter() {
        return idAcceptanceLetter;
    }

    public void setIdAcceptanceLetter(int idAcceptanceLetter) {
        this.idAcceptanceLetter = idAcceptanceLetter;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getAcceptanceFileBase64() {
        return acceptanceFileBase64;
    }

    public void setAcceptanceFileBase64(String acceptanceFileBase64) {
        this.acceptanceFileBase64 = acceptanceFileBase64;
    }   
}