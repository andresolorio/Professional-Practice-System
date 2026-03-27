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
import mx.uv.lis.professionalpracticesystem.logic.DataTransferObject.LinkedOrganizationDTO;
import mx.uv.lis.professionalpracticesystem.logic.Interfaces.ILinkedOrganizationDAO;

public class LinkedOrganizationDAO implements ILinkedOrganizationDAO {

    @Override
    public int saveLinkedOrganization(LinkedOrganizationDTO organization) {
        String sql = "INSERT INTO OrganizacionVinculada (nombreEmpresa, direccion, telefono, ciudad, " +
                     "correoElectronico, sector, usuariosDirectos, usuariosIndirectos) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int result = 0;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, organization.getName());
            preparedStatement.setString(2, organization.getAddress());
            preparedStatement.setString(3, organization.getPhoneNumber());
            preparedStatement.setString(4, organization.getCity());
            preparedStatement.setString(5, organization.getEmail());
            preparedStatement.setString(6, organization.getSector());
            preparedStatement.setString(7, organization.getDirectUsers());
            preparedStatement.setString(8, organization.getIndirectUsers());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(LinkedOrganizationDAO.class.getName()).log(Level.SEVERE, "SQL Error: Fallo al guardar", e);
        }
        return result;
    }

    @Override
    public int updateLinkedOrganization(LinkedOrganizationDTO organization) {
        String sql = "UPDATE OrganizacionVinculada SET nombreEmpresa = ?, direccion = ?, telefono = ?, " +
                     "ciudad = ?, correoElectronico = ?, sector = ?, usuariosDirectos = ?, " +
                     "usuariosIndirectos = ? WHERE idOrganizacionVinculada = ?";
        int result = 0;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, organization.getName());
            preparedStatement.setString(2, organization.getAddress());
            preparedStatement.setString(3, organization.getPhoneNumber());
            preparedStatement.setString(4, organization.getCity());
            preparedStatement.setString(5, organization.getEmail());
            preparedStatement.setString(6, organization.getSector());
            preparedStatement.setString(7, organization.getDirectUsers());
            preparedStatement.setString(8, organization.getIndirectUsers());
            preparedStatement.setInt(9, organization.getIdLinkedOrganization());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(LinkedOrganizationDAO.class.getName()).log(Level.SEVERE, "SQL Error: La actualizacion fallo", e);
        }
        return result;
    }

    @Override
    public List<LinkedOrganizationDTO> getAllLinkedOrganizations() {
        String sql = "SELECT * FROM OrganizacionVinculada";
        List<LinkedOrganizationDTO> organizations = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
                LinkedOrganizationDTO organization = new LinkedOrganizationDTO();
                organization.setIdLinkedOrganization(resultSet.getInt("idOrganizacionVinculada"));
                organization.setName(resultSet.getString("nombreEmpresa"));
                organization.setAddress(resultSet.getString("direccion"));
                organization.setPhoneNumber(resultSet.getString("telefono"));
                organization.setCity(resultSet.getString("ciudad"));
                organization.setEmail(resultSet.getString("correoElectronico"));
                organization.setSector(resultSet.getString("sector"));
                organization.setDirectUsers(resultSet.getString("usuariosDirectos"));
                organization.setIndirectUsers(resultSet.getString("usuariosIndirectos"));
                organizations.add(organization);
            }
        } catch (SQLException e) {
            Logger.getLogger(LinkedOrganizationDAO.class.getName()).log(Level.SEVERE, "SQL Error: No se pudo seleccinoar", e);
        }
        return organizations;
    }

    @Override
    public LinkedOrganizationDTO getLinkedOrganizationById(int idLinkedOrganization) {
        String sql = "SELECT * FROM OrganizacionVinculada WHERE idOrganizacionVinculada = ?";
        LinkedOrganizationDTO organization = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setInt(1, idLinkedOrganization);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    organization = new LinkedOrganizationDTO();
                    organization.setIdLinkedOrganization(resultSet.getInt("idOrganizacionVinculada"));
                    organization.setName(resultSet.getString("nombreEmpresa"));
                    organization.setAddress(resultSet.getString("direccion"));
                    organization.setPhoneNumber(resultSet.getString("telefono"));
                    organization.setCity(resultSet.getString("ciudad"));
                    organization.setEmail(resultSet.getString("correoElectronico"));
                    organization.setSector(resultSet.getString("sector"));
                    organization.setDirectUsers(resultSet.getString("usuariosDirectos"));
                    organization.setIndirectUsers(resultSet.getString("usuariosIndirectos"));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(LinkedOrganizationDAO.class.getName()).log(Level.SEVERE, "SQL Error: No se pudo seleccionar por ID", e);
        }
        return organization;
    }
}
