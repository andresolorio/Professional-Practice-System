package mx.uv.lis.professionalpracticesystem.logic.Interfaces;

import java.util.List;
import mx.uv.lis.professionalpracticesystem.logic.DataTransferObject.ProjectDTO;

public interface IProjectDAO {
    public int saveProject(ProjectDTO project);
    
    public List<ProjectDTO> getAllAvailableProjects();
    
    public ProjectDTO getProjectById(int idProject);
    
    public int updateProject(ProjectDTO project);
}
