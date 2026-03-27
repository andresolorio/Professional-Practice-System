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
import mx.uv.lis.professionalpracticesystem.logic.DataTransferObject.ReportDTO;
import mx.uv.lis.professionalpracticesystem.logic.Interfaces.IReportDAO;

public class ReportDAO implements IReportDAO {

    @Override
    public int saveReport(ReportDTO report) {
        String sql = "INSERT INTO Reporte (nombreReporte, fechaEntrega, horasReportadas, " +
                     "tipoReporte, archivo, matricula) VALUES (?, ?, ?, ?, ?, ?)";
        int result = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, report.getReportName());
            preparedStatement.setDate(2, new java.sql.Date(report.getDeliveryDate().getTime()));
            preparedStatement.setInt(3, report.getReportedHours());
            preparedStatement.setString(4, report.getReportType());
            preparedStatement.setBytes(5, report.getFileContent());
            preparedStatement.setString(6, report.getStudentEnrollment());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, "SQL Error: Fallo en salvar reporte", e);
        }
        return result;
    }

    @Override
    public List<ReportDTO> getReportsByEnrollment(String enrollment) {
        String sql = "SELECT * FROM Reporte WHERE matricula = ?";
        List<ReportDTO> reports = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, enrollment);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ReportDTO report = new ReportDTO();
                    report.setIdReport(resultSet.getInt("idReporte"));
                    report.setReportName(resultSet.getString("nombreReporte"));
                    report.setDeliveryDate(resultSet.getDate("fechaEntrega"));
                    report.setReportedHours(resultSet.getInt("horasReportadas"));
                    report.setReportType(resultSet.getString("tipoReporte"));
                    report.setStudentEnrollment(resultSet.getString("matricula"));
                    reports.add(report);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, "SQL Error: Fallo al obtener reporte por matricula", e);
        }
        return reports;
    }

    @Override
    public ReportDTO getReportById(int idReport) {
        String sql = "SELECT * FROM Reporte WHERE idReporte = ?";
        ReportDTO report = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setInt(1, idReport);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    report = new ReportDTO();
                    report.setIdReport(resultSet.getInt("idReporte"));
                    report.setReportName(resultSet.getString("nombreReporte"));
                    report.setDeliveryDate(resultSet.getDate("fechaEntrega"));
                    report.setReportedHours(resultSet.getInt("horasReportadas"));
                    report.setReportType(resultSet.getString("tipoReporte"));
                    report.setStudentEnrollment(resultSet.getString("matricula"));
                    
                    report.setFileContent(resultSet.getBytes("archivo"));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, 
                "SQL Error: Failed en recuperar el reporte por ID: " + idReport, e);
        }
        return report;
    }
    
}
