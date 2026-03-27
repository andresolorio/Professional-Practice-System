package mx.uv.lis.professionalpracticesystem.logic.Interfaces;

import java.util.List;
import mx.uv.lis.professionalpracticesystem.logic.DataTransferObject.LinkedOrganizationDTO;

public interface ILinkedOrganizationDAO {
    public int saveLinkedOrganization(LinkedOrganizationDTO organization);
    
    public int updateLinkedOrganization(LinkedOrganizationDTO organization);
    
    public List<LinkedOrganizationDTO> getAllLinkedOrganizations();
    
    public LinkedOrganizationDTO getLinkedOrganizationById(int idLinkedOrganization);
}
