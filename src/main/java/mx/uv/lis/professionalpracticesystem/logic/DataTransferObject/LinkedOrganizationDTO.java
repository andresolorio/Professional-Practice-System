package mx.uv.lis.professionalpracticesystem.logic.DataTransferObject;

public class LinkedOrganizationDTO {
    private int idLinkedOrganization;
    private String name;
    private String address;
    private String phoneNumber;
    private String city;
    private String email;
    private String sector;
    private String directUsers;
    private String indirectUsers;
    
    public LinkedOrganizationDTO() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getDirectUsers() {
        return directUsers;
    }

    public void setDirectUsers(String directUsers) {
        this.directUsers = directUsers;
    }

    public String getIndirectUsers() {
        return indirectUsers;
    }

    public void setIndirectUsers(String indirectUsers) {
        this.indirectUsers = indirectUsers;
    }
}
