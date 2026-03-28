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
import mx.uv.lis.professionalpracticesystem.logic.DataTransferObject.EducativeExperienceDTO;
import mx.uv.lis.professionalpracticesystem.logic.Interfaces.IEducativeExperienceDAO;

public class EducativeExperienceDAO implements IEducativeExperienceDAO {
    private static final Logger LOGGER = Logger.getLogger(EducativeExperienceDAO.class.getName());

    @Override
    public int registerExperienceEducative(EducativeExperienceDTO experience) {
        int result = 0;
        String query = "INSERT INTO experienceeducative (nrc, name) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, experience.getNrc());
            statement.setString(2, experience.getName());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al registrar experiencia educativa", e.getMessage());
        }
        return result;
    }

    @Override
    public List<EducativeExperienceDTO> getAllExperiencesEducative() {
        List<EducativeExperienceDTO> experiences = new ArrayList<>();
        String query = "SELECT nrc, name FROM experienceeducative";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                EducativeExperienceDTO experience = new EducativeExperienceDTO();
                experience.setNrc(resultSet.getString("nrc"));
                experience.setName(resultSet.getString("name"));
                experiences.add(experience);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al recuperar todas las experiencias", e.getMessage());
        }
        return experiences;
    }

    @Override
    public EducativeExperienceDTO getExperienceEducativeByNrc(String nrc) {
        EducativeExperienceDTO experience = null;
        String query = "SELECT nrc, name FROM experienceeducative WHERE nrc = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nrc);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    experience = new EducativeExperienceDTO();
                    experience.setNrc(resultSet.getString("nrc"));
                    experience.setName(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al recuperar una experiencia por NRC", e.getMessage());
        }
        return experience;
    }

    @Override
    public int updateExperienceEducative(EducativeExperienceDTO experience) {
        int result = 0;
        String query = "UPDATE experienceeducative SET name = ? WHERE nrc = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, experience.getName());
            statement.setString(2, experience.getNrc());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar experiencia", e.getMessage());
        }
        return result;
    }   
}