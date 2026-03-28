package mx.uv.lis.professionalpracticesystem.logic.Interfaces;

import java.util.List;
import mx.uv.lis.professionalpracticesystem.logic.DataTransferObject.ActivityDTO;

public interface IActivityDAO {
    public int registerActivity(ActivityDTO activity);
    
    public List<ActivityDTO> getActivitiesByProject(int idProject);
    
    public ActivityDTO getActivityById(int idActivity);
    
    public int updateActivity(ActivityDTO activity);
}
