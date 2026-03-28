package mx.uv.lis.professionalpracticesystem.logic.Interfaces;

import java.util.List;
import mx.uv.lis.professionalpracticesystem.logic.DataTransferObject.AcceptanceLetterDTO;

public interface IAcceptanceLetterDAO {
    public int registerAcceptanceLetter(AcceptanceLetterDTO acceptanceLetter);
    
    public List<AcceptanceLetterDTO> getAllAcceptanceLetters();
    
    public AcceptanceLetterDTO getAcceptanceLetterByEnrollment(String enrollment);
    
    public int updateAcceptanceLetter(AcceptanceLetterDTO acceptanceLetter);
}
