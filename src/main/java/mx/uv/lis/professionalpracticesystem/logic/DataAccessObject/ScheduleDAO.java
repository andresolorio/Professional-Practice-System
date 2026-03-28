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
import mx.uv.lis.professionalpracticesystem.logic.DataTransferObject.ScheduleDTO;
import mx.uv.lis.professionalpracticesystem.logic.Interfaces.IScheduleDAO;

public class ScheduleDAO implements IScheduleDAO{
    private static final Logger LOGGER = Logger.getLogger(ScheduleDAO.class.getName());

    @Override
    public int registerSchedule(ScheduleDTO schedule) {
        int result = 0;
        String query = "INSERT INTO horario (dia, horaInicio, horaFin) VALUES (?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, schedule.getDay());
            statement.setTime(2, schedule.getStartTime());
            statement.setTime(3, schedule.getEndTime());
            
            result = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al registrar horario", e.getMessage());
        }
        return result;
    }

    @Override
    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleDTO> schedules = new ArrayList<>();
        String query = "SELECT * FROM horario";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                ScheduleDTO schedule = new ScheduleDTO();
                schedule.setIdSchedule(resultSet.getInt("idHorario"));
                schedule.setDay(resultSet.getString("dia"));
                schedule.setStartTime(resultSet.getTime("horaInicio"));
                schedule.setEndTime(resultSet.getTime("horaFin"));
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al recuperar todos los horarios", e.getMessage());
        }
        return schedules;
    }

    @Override
    public ScheduleDTO getScheduleById(int idSchedule) {
        ScheduleDTO schedule = null;
        String query = "SELECT * FROM horario WHERE idHorario = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, idSchedule);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    schedule = new ScheduleDTO();
                    schedule.setIdSchedule(resultSet.getInt("idHorario"));
                    schedule.setDay(resultSet.getString("dia"));
                    schedule.setStartTime(resultSet.getTime("horaInicio"));
                    schedule.setEndTime(resultSet.getTime("horaFin"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al recuperar el horario por id", e.getMessage());
        }
        return schedule;
    }

    @Override
    public int updateSchedule(ScheduleDTO schedule) {
        int result = 0;
        String query = "UPDATE horario SET dia = ?, horaInicio = ?, horaFin = ? WHERE idHorario = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, schedule.getDay());
            statement.setTime(2, schedule.getStartTime());
            statement.setTime(3, schedule.getEndTime());
            statement.setInt(4, schedule.getIdSchedule());
            
            result = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al actulizar horario", e.getMessage());
        }
        return result;
    }
}