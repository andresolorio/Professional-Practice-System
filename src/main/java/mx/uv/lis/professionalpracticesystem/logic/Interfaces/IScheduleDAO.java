package mx.uv.lis.professionalpracticesystem.logic.Interfaces;

import java.util.List;
import mx.uv.lis.professionalpracticesystem.logic.DataTransferObject.ScheduleDTO;

public interface IScheduleDAO {
    public int registerSchedule(ScheduleDTO schedule);
    
    public List<ScheduleDTO> getAllSchedules();
    
    public ScheduleDTO getScheduleById(int idSchedule);
    
    public int updateSchedule(ScheduleDTO schedule);
}
