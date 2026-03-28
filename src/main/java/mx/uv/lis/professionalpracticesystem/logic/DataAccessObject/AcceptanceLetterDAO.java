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
import mx.uv.lis.professionalpracticesystem.logic.DataTransferObject.AcceptanceLetterDTO;
import mx.uv.lis.professionalpracticesystem.logic.Interfaces.IAcceptanceLetterDAO;

public class AcceptanceLetterDAO implements IAcceptanceLetterDAO {
    private static final Logger LOGGER = Logger.getLogger(AcceptanceLetterDAO.class.getName());
    
    @Override
    public int registerAcceptanceLetter(AcceptanceLetterDTO acceptanceLetter) {
        int result = 0;
        String query = "INSERT INTO oficioaceptacion (matricula, fechaEmision, archivoOficioAceptacionPDF) VALUES (?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, acceptanceLetter.getEnrollment());
            statement.setDate(2, acceptanceLetter.getIssueDate());
            statement.setString(3, acceptanceLetter.getAcceptanceFileBase64());
            
            result = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al registrar oficio de aceptacion", e.getMessage());
        }
        return result;
    }

    @Override
    public List<AcceptanceLetterDTO> getAllAcceptanceLetters() {
        List<AcceptanceLetterDTO> letters = new ArrayList<>();
        String query = "SELECT * FROM oficioaceptacion";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                AcceptanceLetterDTO letter = new AcceptanceLetterDTO();
                letter.setIdAcceptanceLetter(resultSet.getInt("idOficioAceptacion"));
                letter.setEnrollment(resultSet.getString("matricula"));
                letter.setIssueDate(resultSet.getDate("fechaEmision"));
                letter.setAcceptanceFileBase64(resultSet.getString("archivoOficioAceptacionPDF"));
                letters.add(letter);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al recuperar oficios de aceptacion", e.getMessage());
        }
        return letters;
    }

    @Override
    public AcceptanceLetterDTO getAcceptanceLetterByEnrollment(String enrollment) {
        AcceptanceLetterDTO letter = null;
        String query = "SELECT * FROM oficioaceptacion WHERE matricula = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, enrollment);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    letter = new AcceptanceLetterDTO();
                    letter.setIdAcceptanceLetter(resultSet.getInt("idOficioAceptacion"));
                    letter.setEnrollment(resultSet.getString("matricula"));
                    letter.setIssueDate(resultSet.getDate("fechaEmision"));
                    letter.setAcceptanceFileBase64(resultSet.getString("archivoOficioAceptacionPDF"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al recuperar oficio de aceptacion por matricula", e.getMessage());
        }
        return letter;
    }

    @Override
    public int updateAcceptanceLetter(AcceptanceLetterDTO acceptanceLetter) {
        int result = 0;
        String query = "UPDATE oficioaceptacion SET fechaEmision = ?, archivoOficioAceptacionPDF = ? WHERE idOficioAceptacion = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setDate(1, acceptanceLetter.getIssueDate());
            statement.setString(2, acceptanceLetter.getAcceptanceFileBase64());
            statement.setInt(3, acceptanceLetter.getIdAcceptanceLetter());
            
            result = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar oficio de aceptacion", e.getMessage());
        }
        return result;
    }
}