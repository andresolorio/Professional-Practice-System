package mx.uv.lis.professionalpracticesystem.logic.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.uv.lis.professionalpracticesystem.dataaccess.DatabaseConnection;
import mx.uv.lis.professionalpracticesystem.logic.DataTransferObject.ProjectDTO;
import mx.uv.lis.professionalpracticesystem.logic.Interfaces.IProjectDAO;

public class ProjectDAO implements IProjectDAO {

    @Override
    public int saveProject(ProjectDTO project) {
        String sql = "INSERT INTO Proyecto (nombreProyecto, descripcion, metodologia, " +
                     "objetivoGeneral, cupo, estado, idOrganizacionVinculada) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        int result = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setString(3, project.getMethodology());
            preparedStatement.setString(4, project.getGeneralObjective());
            preparedStatement.setInt(5, project.getVacancy());
            preparedStatement.setString(6, "Activo");
            preparedStatement.setInt(7, project.getIdLinkedOrganization());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, "SQL Error: Fallo en guardar proyecto", e);
        }
        return result;
    }

    @Override
    public List<ProjectDTO> getAllAvailableProjects() {
        String sql = "SELECT * FROM Proyecto";
        List<ProjectDTO> projectsList = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                ProjectDTO project = new ProjectDTO();
                project.setIdProject(resultSet.getInt("idProyecto"));
                project.setProjectName(resultSet.getString("nombreProyecto"));
                project.setDescription(resultSet.getString("descripcion"));
                project.setMethodology(resultSet.getString("metodologia"));
                project.setGeneralObjective(resultSet.getString("objetivoGeneral"));
                project.setImmediateObjective(resultSet.getString("objetivoInmediato"));
                project.setMediatedObjective(resultSet.getString("objetivoMediato"));
                project.setDuration(resultSet.getString("duracion"));
                project.setResponsibilities(resultSet.getString("responsabilidades"));
                project.setResources(resultSet.getString("recursos"));
                project.setStatus(resultSet.getString("estado"));
                project.setIdLinkedOrganization(resultSet.getInt("idOrganizacionVinculada"));
            
                projectsList.add(project);
            }

        } catch (SQLException e) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, "SQL Error: Seleccion fallida", e);
        }
        return projectsList;
    }

    @Override
    public ProjectDTO getProjectById(int idProject) {
        String sql = "SELECT * FROM Proyecto WHERE idProyecto = ?";
        ProjectDTO project = null;

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        
            preparedStatement.setInt(1, idProject);
        
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    project = new ProjectDTO();
                    project.setIdProject(resultSet.getInt("idProyecto"));
                    project.setProjectName(resultSet.getString("nombreProyecto"));
                    project.setDescription(resultSet.getString("descripcion"));
                    project.setMethodology(resultSet.getString("metodologia"));
                    project.setGeneralObjective(resultSet.getString("objetivoGeneral"));
                    project.setImmediateObjective(resultSet.getString("objetivoInmediato"));
                    project.setMediatedObjective(resultSet.getString("objetivoMediato"));
                    project.setDuration(resultSet.getString("duracion"));
                    project.setResponsibilities(resultSet.getString("responsabilidades"));
                    project.setResources(resultSet.getString("recursos"));
                    project.setStatus(resultSet.getString("estado"));
                    project.setIdLinkedOrganization(resultSet.getInt("idOrganizacionVinculada"));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, 
                "SQL Error: No se pudo recuperar el proyecto por ID " + idProject, e);
        }
    
        return project;
    }

    @Override
    public int updateProject(ProjectDTO project) {
            String sql = "UPDATE Proyecto SET nombreProyecto = ?, descripcion = ?, metodologia = ?, " +
                     "objetivoGeneral = ?, cupo = ? WHERE idProyecto = ?";
        int rowsAffected = 0;

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setString(3, project.getMethodology());
            preparedStatement.setString(4, project.getGeneralObjective());
            preparedStatement.setInt(5, project.getVacancy());
            preparedStatement.setInt(6, project.getIdProject());

            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, "SQL Error: la actulizacion de datos fallo", e);
        }
        return rowsAffected;
    }
}
