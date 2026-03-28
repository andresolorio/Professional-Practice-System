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
import mx.uv.lis.professionalpracticesystem.logic.DataTransferObject.ActivityDTO;
import mx.uv.lis.professionalpracticesystem.logic.Interfaces.IActivityDAO;

public class ActivityDAO implements IActivityDAO  {
    private static final Logger LOGGER = Logger.getLogger(ActivityDAO.class.getName());

    @Override
    public int registerActivity(ActivityDTO activity) {
        int result = 0;
        String query = "INSERT INTO actividad (nombreActividad, descripcion, fechaEntrega, idProyecto) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, activity.getActivityName());
            statement.setString(2, activity.getDescription());
            statement.setTimestamp(3, activity.getDeliveryDate());
            statement.setInt(4, activity.getIdProject());
            
            result = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al registrar actividad", e.getMessage());
        }
        return result;
    }

    @Override
    public List<ActivityDTO> getActivitiesByProject(int idProject) {
        List<ActivityDTO> activities = new ArrayList<>();
        String query = "SELECT * FROM actividad WHERE idProyecto = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, idProject);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ActivityDTO activity = new ActivityDTO();
                    activity.setIdActivity(resultSet.getInt("idActividad"));
                    activity.setActivityName(resultSet.getString("nombreActividad"));
                    activity.setDescription(resultSet.getString("descripcion"));
                    activity.setDeliveryDate(resultSet.getTimestamp("fechaEntrega"));
                    activity.setIdProject(resultSet.getInt("idProyecto"));
                    activities.add(activity);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al recuperar actividad segun el idProyecto", e.getMessage());
        }
        return activities;
    }

    @Override
    public ActivityDTO getActivityById(int idActivity) {
        ActivityDTO activity = null;
        String query = "SELECT * FROM actividad WHERE idActividad = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, idActivity);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    activity = new ActivityDTO();
                    activity.setIdActivity(resultSet.getInt("idActividad"));
                    activity.setActivityName(resultSet.getString("nombreActividad"));
                    activity.setDescription(resultSet.getString("descripcion"));
                    activity.setDeliveryDate(resultSet.getTimestamp("fechaEntrega"));
                    activity.setIdProject(resultSet.getInt("idProyecto"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al recuperar actividad por id", e.getMessage());
        }
        return activity;
    }

    @Override
    public int updateActivity(ActivityDTO activity) {
        int result = 0;
        String query = "UPDATE actividad SET nombreActividad = ?, descripcion = ?, fechaEntrega = ? WHERE idActividad = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, activity.getActivityName());
            statement.setString(2, activity.getDescription());
            statement.setTimestamp(3, activity.getDeliveryDate());
            statement.setInt(4, activity.getIdActivity());
            
            result = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar actividad", e.getMessage());
        }
        return result;
    }    
}
