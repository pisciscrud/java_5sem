package by.stalmakhova.services;


import by.stalmakhova.dto.NoteHistory;
import by.stalmakhova.dto.ScheduleDto;
import by.stalmakhova.entity.*;
import by.stalmakhova.repositories.ScheduleRepository;
import by.stalmakhova.repositories.StatusRepository;
import by.stalmakhova.repositories.UserRepository;
import by.stalmakhova.services.Interfaces.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    public final ScheduleRepository scheduleRepository;
    private StatusRepository statusRepository;

    @Autowired
    public ScheduleServiceImpl(ModelMapper modelMapper, UserRepository userRepository, ScheduleRepository scheduleRepository,StatusRepository statusRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
        this.statusRepository= statusRepository;
    }
@Override
    public Collection<ScheduleDto> getAllSchedule() {
        var schedules = scheduleRepository.findAll();

        var schedulesDto = new ArrayList<ScheduleDto>();

        for (var schedule : schedules) {
            var scheduleDto = this.modelMapper.map(schedule, ScheduleDto.class);
            schedulesDto.add(scheduleDto);
        }

        return schedulesDto;

    }
 @Override
    public Schedule CreateNote(Long idUser, String date, String time, Pet pet, Master master, ProcedureTable procedure, Status Status) {
     var note = new Schedule();
     var currentUser = userRepository.findById(idUser).orElse(null);
     if (currentUser == null)
         throw new IllegalArgumentException("User with id " + idUser + " not found");

     note.setOwner(currentUser);
     note.setDate(date);
     note.setTime(time);
     note.setMaster(master);
     note.setProcedure(procedure);
     note.setStatus(Status);
     note.setPet(pet);
     return note;
 }
 @Override
    public Schedule SaveNote (Schedule schedule) {
     return scheduleRepository.save(schedule);
 }


    @Override
    public Collection<NoteHistory> getAllNotesForCurrentUser(Long id)
    {
        var currentUser = userRepository.findById(id).orElse(null);
        if (currentUser == null)
            throw new IllegalArgumentException("User with id " + id + " not found");
        var allNotes = new ArrayList<NoteHistory>();

       var notesFromBD= scheduleRepository.findScheduleByOwnerId(currentUser.getId());
        notesFromBD.forEach(note->allNotes.add(modelMapper.map(note,NoteHistory.class)));
        return allNotes;
    }

    @Override
    public Collection<ScheduleDto> getAllAplicationsWhatWaiting()
    {
        var allNotes = new ArrayList<ScheduleDto>();
        var notesFromBD= scheduleRepository.findScheduleByStatusId(4l);
        notesFromBD.forEach(note->allNotes.add(modelMapper.map(note,ScheduleDto.class)));
        return allNotes;
    }


    @Override
    public ScheduleDto setNoteStatus(Long id, Long status_id ) {
        //найти note по id и установить ему статус status_id
        var note = scheduleRepository.findById(id).orElse(null);
        if (note == null)
            throw new IllegalArgumentException("Note with id " + id + " not found");
        var status =statusRepository.findStatusById(status_id);
        note.setStatus(status);
        //обнови в бд
        scheduleRepository.save(note);
        return modelMapper.map(note,ScheduleDto.class);
    }

}
