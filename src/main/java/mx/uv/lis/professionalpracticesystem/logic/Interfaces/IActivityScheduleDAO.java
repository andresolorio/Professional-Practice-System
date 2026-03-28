package mx.uv.lis.professionalpracticesystem.logic.Interfaces;

import java.util.List;
import mx.uv.lis.professionalpracticesystem.logic.DataTransferObject.ActivityScheduleDTO;

public interface IActivityScheduleDAO {
    public int registerActivitySchedule(ActivityScheduleDTO schedule);
    
    public List<ActivityScheduleDTO> getActivitySchedulesByProject(int idProject);
    
    public ActivityScheduleDTO getActivityScheduleByEnrollment(String enrollment);
    
    public int updateActivitySchedule(ActivityScheduleDTO schedule);
}
