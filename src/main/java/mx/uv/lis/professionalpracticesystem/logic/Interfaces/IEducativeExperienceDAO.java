package mx.uv.lis.professionalpracticesystem.logic.Interfaces;

import java.util.List;
import mx.uv.lis.professionalpracticesystem.logic.DataTransferObject.EducativeExperienceDTO;

public interface IEducativeExperienceDAO {
    public int registerExperienceEducative(EducativeExperienceDTO experience);
    
    public List<EducativeExperienceDTO> getAllExperiencesEducative();
    
    public EducativeExperienceDTO getExperienceEducativeByNrc(String nrc);
    
    public int updateExperienceEducative(EducativeExperienceDTO experience);
}
