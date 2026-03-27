package mx.uv.lis.professionalpracticesystem.logic.Interfaces;

import java.util.List;
import mx.uv.lis.professionalpracticesystem.logic.DataTransferObject.CoordinatorDTO;

public interface ICoordinatorDAO {
    public int saveCoordinator(CoordinatorDTO coordinator);
    
    public int updateCoordinator(CoordinatorDTO coordinator);
    
    public CoordinatorDTO getCoordinatorByStaffNumber(int staffNumber);
    
    public List<CoordinatorDTO> getAllCoordinators();
}
