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
import mx.uv.lis.professionalpracticesystem.logic.DataTransferObject.ActivityScheduleDTO;
import mx.uv.lis.professionalpracticesystem.logic.Interfaces.IActivityScheduleDAO;

public class ActivityScheduleDAO implements IActivityScheduleDAO {
    private static final Logger LOGGER = Logger.getLogger(ActivityScheduleDAO.class.getName());

    @Override
    public int registerActivitySchedule(ActivityScheduleDTO schedule) {
        int result = 0;
        String query = "INSERT INTO cronogramadeactividades (idProyecto, matricula, fechaAprobacion, archivoCronogramaPDF) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, schedule.getIdProject());
            statement.setString(2, schedule.getEnrollment());
            statement.setDate(3, schedule.getApprovalDate());
            statement.setString(4, schedule.getScheduleFileBase64());
            
            result = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al registrar Cronograma de actividades", e.getMessage());
        }
        return result;
    }

    @Override
    public List<ActivityScheduleDTO> getActivitySchedulesByProject(int idProject) {
        List<ActivityScheduleDTO> schedules = new ArrayList<>();
        String query = "SELECT * FROM cronogramadeactividades WHERE idProyecto = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, idProject);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ActivityScheduleDTO schedule = new ActivityScheduleDTO();
                    schedule.setIdActivitySchedule(resultSet.getInt("idCronogramaActividades"));
                    schedule.setIdProject(resultSet.getInt("idProyecto"));
                    schedule.setEnrollment(resultSet.getString("matricula"));
                    schedule.setApprovalDate(resultSet.getDate("fechaAprobacion"));
                    schedule.setScheduleFileBase64(resultSet.getString("archivoCronogramaPDF"));
                    schedules.add(schedule);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al recuperar Cronograma de Actividad por idProyecto", e.getMessage());
        }
        return schedules;
    }

    @Override
    public ActivityScheduleDTO getActivityScheduleByEnrollment(String enrollment) {
        ActivityScheduleDTO schedule = null;
        String query = "SELECT * FROM cronogramadeactividades WHERE matricula = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, enrollment);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    schedule = new ActivityScheduleDTO();
                    schedule.setIdActivitySchedule(resultSet.getInt("idCronogramaActividades"));
                    schedule.setIdProject(resultSet.getInt("idProyecto"));
                    schedule.setEnrollment(resultSet.getString("matricula"));
                    schedule.setApprovalDate(resultSet.getDate("fechaAprobacion"));
                    schedule.setScheduleFileBase64(resultSet.getString("archivoCronogramaPDF"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al recuperar Cronograma de Actividades por Matricula", e.getMessage());
        }
        return schedule;
    }

    @Override
    public int updateActivitySchedule(ActivityScheduleDTO schedule) {
        int result = 0;
        String query = "UPDATE cronogramadeactividades SET fechaAprobacion = ?, archivoCronogramaPDF = ? WHERE idCronogramaActividades = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setDate(1, schedule.getApprovalDate());
            statement.setString(2, schedule.getScheduleFileBase64());
            statement.setInt(3, schedule.getIdActivitySchedule());
            
            result = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar Cronograma de Actividades", e.getMessage());
        }
        return result;
    }
}
