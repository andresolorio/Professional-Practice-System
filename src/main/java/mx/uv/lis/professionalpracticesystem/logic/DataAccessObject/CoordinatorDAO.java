package mx.uv.lis.professionalpracticesystem.logic.DataAccessObject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.uv.lis.professionalpracticesystem.dataaccess.DatabaseConnection;
import mx.uv.lis.professionalpracticesystem.logic.DataTransferObject.CoordinatorDTO;
import mx.uv.lis.professionalpracticesystem.logic.Interfaces.ICoordinatorDAO;

public class CoordinatorDAO implements ICoordinatorDAO {

    @Override
    public int saveCoordinator(CoordinatorDTO coordinator) {
        String sql = "INSERT INTO Coordinador (numeroPersonalCoordinador, nombre, primerApellido, " + 
                     "segundoApellido, contraseña, fechaRegistro, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int result = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setInt(1, coordinator.getStaffNumber());
            preparedStatement.setString(2, coordinator.getName());
            preparedStatement.setString(3, coordinator.getFirstName());
            preparedStatement.setString(4, coordinator.getLastName());
            preparedStatement.setString(5, coordinator.getPassword());
            preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(7, "Activo");

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CoordinatorDAO.class.getName()).log(Level.SEVERE, 
                "SQL Error: Fallo al registrar coordinador", e);
        }
        return result;
    }

    @Override
    public int updateCoordinator(CoordinatorDTO coordinator) {
        String sql = "UPDATE Coordinador SET nombre = ?, primerApellido = ?, " +
                     "segundoApellido = ?, contraseña = ?, estado = ? " +
                     "WHERE numeroPersonalCoordinador = ?";
        int result = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, coordinator.getName());
            preparedStatement.setString(2, coordinator.getFirstName());
            preparedStatement.setString(3, coordinator.getLastName());
            preparedStatement.setString(4, coordinator.getPassword());
            preparedStatement.setString(5, coordinator.getStatus());
            preparedStatement.setInt(6, coordinator.getStaffNumber());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CoordinatorDAO.class.getName()).log(Level.SEVERE, 
                "SQL Error: Update of coordinator failed", e);
        }
        return result;
    }

    @Override
    public CoordinatorDTO getCoordinatorByStaffNumber(int staffNumber) {
        String sql = "SELECT * FROM Coordinador WHERE numeroPersonalCoordinador = ?";
        CoordinatorDTO coordinator = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setInt(1, staffNumber);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    coordinator = new CoordinatorDTO();
                    coordinator.setStaffNumber(resultSet.getInt("numeroPersonalCoordinador"));
                    coordinator.setName(resultSet.getString("nombre"));
                    coordinator.setFirstName(resultSet.getString("primerApellido"));
                    coordinator.setLastName(resultSet.getString("segundoApellido"));
                    coordinator.setPassword(resultSet.getString("contraseña"));
                    coordinator.setStatus(resultSet.getString("estado"));
                    coordinator.setRegistrationDate((Date) new java.util.Date(resultSet.getTimestamp("fechaRegistro").getTime()));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(CoordinatorDAO.class.getName()).log(Level.SEVERE, 
                "SQL Error: Fallo en recuperar el coordinador con numero de staff", e);
        }
        return coordinator;
    }

    @Override
    public List<CoordinatorDTO> getAllCoordinators() {
        String sql = "SELECT * FROM Coordinador";
        List<CoordinatorDTO> coordinatorsList = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                CoordinatorDTO coordinator = new CoordinatorDTO();
                coordinator.setStaffNumber(resultSet.getInt("numeroPersonalCoordinador"));
                coordinator.setName(resultSet.getString("nombre"));
                coordinator.setFirstName(resultSet.getString("primerApellido"));
                coordinator.setLastName(resultSet.getString("segundoApellido"));
                coordinator.setStatus(resultSet.getString("estado"));
                coordinator.setRegistrationDate((Date) new java.util.Date(resultSet.getTimestamp("fechaRegistro").getTime()));
                
                coordinatorsList.add(coordinator);
            }
        } catch (SQLException e) {
            Logger.getLogger(CoordinatorDAO.class.getName()).log(Level.SEVERE, 
                "SQL Error: Fallo al recuperar todos los coordinadores", e);
        }
        return coordinatorsList;
    }
}
