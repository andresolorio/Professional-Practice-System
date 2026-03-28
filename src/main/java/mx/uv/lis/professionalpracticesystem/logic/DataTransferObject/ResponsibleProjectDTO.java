package mx.uv.lis.professionalpracticesystem.logic.DataTransferObject;

public class ResponsibleProjectDTO {
    private int idResponsible;
    private int idLinkedOrganization;
    private String name;
    private String firstName;
    private String lastName;
    private String charge;

    public ResponsibleProjectDTO() {
    }

    public int getIdResponsible() {
        return idResponsible;
    }

    public void setIdResponsible(int idResponsible) {
        this.idResponsible = idResponsible;
    }

    public int getIdLinkedOrganization() {
        return idLinkedOrganization;
    }

    public void setIdLinkedOrganization(int idLinkedOrganization) {
        this.idLinkedOrganization = idLinkedOrganization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }
}