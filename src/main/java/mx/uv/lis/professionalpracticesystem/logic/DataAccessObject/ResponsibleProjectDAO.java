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
import mx.uv.lis.professionalpracticesystem.logic.DataTransferObject.ResponsibleProjectDTO;
import mx.uv.lis.professionalpracticesystem.logic.Interfaces.IResponsibleProjectDAO;

public class ResponsibleProjectDAO implements IResponsibleProjectDAO {
    private static final Logger LOGGER = Logger.getLogger(ResponsibleProjectDAO.class.getName());

    @Override
    public int registerResponsible(ResponsibleProjectDTO responsible) {
        int result = 0;
        String query = "INSERT INTO encargado (idOrganizacionVinculada, nombre, primerApellido, segundoApellido, cargo) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, responsible.getIdLinkedOrganization());
            statement.setString(2, responsible.getName());
            statement.setString(3, responsible.getFirstName());
            statement.setString(4, responsible.getLastName());
            statement.setString(5, responsible.getCharge());
            
            result = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al registrar responsable", e.getMessage());
        }
        return result;
    }

    @Override
    public List<ResponsibleProjectDTO> getResponsiblesByOrganization(int idLinkedOrganization) {
        List<ResponsibleProjectDTO> responsibles = new ArrayList<>();
        String query = "SELECT * FROM encargado WHERE idOrganizacionVinculada = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, idLinkedOrganization);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ResponsibleProjectDTO responsible = new ResponsibleProjectDTO();
                    responsible.setIdResponsible(resultSet.getInt("idEncargado"));
                    responsible.setIdLinkedOrganization(resultSet.getInt("idOrganizacionVinculada"));
                    responsible.setName(resultSet.getString("nombre"));
                    responsible.setFirstName(resultSet.getString("primerApellido"));
                    responsible.setLastName(resultSet.getString("segundoApellido"));
                    responsible.setCharge(resultSet.getString("cargo"));
                    responsibles.add(responsible);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al recuperar encargado por idOrganizacion", e.getMessage());
        }
        return responsibles;
    }

    @Override
    public ResponsibleProjectDTO getResponsibleById(int idResponsible) {
        ResponsibleProjectDTO responsible = null;
        String query = "SELECT * FROM encargado WHERE idEncargado = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, idResponsible);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    responsible = new ResponsibleProjectDTO();
                    responsible.setIdResponsible(resultSet.getInt("idEncargado"));
                    responsible.setIdLinkedOrganization(resultSet.getInt("idOrganizacionVinculada"));
                    responsible.setName(resultSet.getString("nombre"));
                    responsible.setFirstName(resultSet.getString("primerApellido"));
                    responsible.setLastName(resultSet.getString("segundoApellido"));
                    responsible.setCharge(resultSet.getString("cargo"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al recuperar encargado por ID", e.getMessage());
        }
        return responsible;
    }

    @Override
    public int updateResponsible(ResponsibleProjectDTO responsible) {
        int result = 0;
        String query = "UPDATE encargado SET nombre = ?, primerApellido = ?, segundoApellido = ?, cargo = ? WHERE idEncargado = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, responsible.getName());
            statement.setString(2, responsible.getFirstName());
            statement.setString(3, responsible.getLastName());
            statement.setString(4, responsible.getCharge());
            statement.setInt(5, responsible.getIdResponsible());
            
            result = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar responsable", e.getMessage());
        }
        return result;
    }
}