package by.stalmakhova.services.Interfaces;

import by.stalmakhova.dto.NoteHistory;
import by.stalmakhova.dto.ScheduleDto;
import by.stalmakhova.entity.*;

import java.util.Collection;

public interface ScheduleService {
    Collection<ScheduleDto> getAllSchedule();
    Schedule getScheduleById(Long id);
    Schedule CreateNote (Long idUser, String date, String time, Pet pet, Master masterId, ProcedureTable procedureId, Status StatusId);
    Schedule SaveNote (Schedule schedule);
    Collection<NoteHistory> getAllNotesForCurrentUser(Long id);

    Collection<ScheduleDto> getAllAplicationsWhatWaiting();

    ScheduleDto setNoteStatus(Long id, Long status_id);
}
