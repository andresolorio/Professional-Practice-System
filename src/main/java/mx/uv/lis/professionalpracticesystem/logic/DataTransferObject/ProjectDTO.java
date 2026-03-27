package mx.uv.lis.professionalpracticesystem.logic.DataTransferObject;

public class ProjectDTO {
    private int idProject;
    private String projectName;
    private String description;
    private String methodology;
    private String generalObjective;
    private String immediateObjective;
    private int vacancy;
    private String status;
    private int idLinkedOrganization;
    private String mediatedObjective;
    private String duration;
    private String responsibilities;
    private String resources;
    
    public ProjectDTO() {
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public String getGeneralObjective() {
        return generalObjective;
    }

    public void setGeneralObjective(String generalObjective) {
        this.generalObjective = generalObjective;
    }

    public String getImmediateObjective() {
        return immediateObjective;
    }

    public void setImmediateObjective(String immediateObjective) {
        this.immediateObjective = immediateObjective;
    }

    public int getVacancy() {
        return vacancy;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdLinkedOrganization() {
        return idLinkedOrganization;
    }

    public void setIdLinkedOrganization(int idLinkedOrganization) {
        this.idLinkedOrganization = idLinkedOrganization;
    }

    public String getMediatedObjective() {
        return mediatedObjective;
    }

    public void setMediatedObjective(String mediatedObjective) {
        this.mediatedObjective = mediatedObjective;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }
}