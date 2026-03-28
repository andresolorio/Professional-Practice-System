package mx.uv.lis.professionalpracticesystem.logic.Interfaces;

import java.util.List;
import mx.uv.lis.professionalpracticesystem.logic.DataTransferObject.ResponsibleProjectDTO;

public interface IResponsibleProjectDAO {
    public int registerResponsible(ResponsibleProjectDTO responsible);
    
    public List<ResponsibleProjectDTO> getResponsiblesByOrganization(int idLinkedOrganization);
    
    public ResponsibleProjectDTO getResponsibleById(int idResponsible);
    
    public int updateResponsible(ResponsibleProjectDTO responsible);
}
