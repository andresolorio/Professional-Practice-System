package mx.uv.lis.professionalpracticesystem.logic.Interfaces;

import java.util.List;
import mx.uv.lis.professionalpracticesystem.logic.DataTransferObject.ReportDTO;

public interface IReportDAO {
    public int saveReport(ReportDTO report);
    
    public List<ReportDTO> getReportsByEnrollment(String enrollment);
    
    public ReportDTO getReportById(int idReport);
}
