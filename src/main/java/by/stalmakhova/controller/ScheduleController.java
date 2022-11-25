package by.stalmakhova.controller;

import by.stalmakhova.dto.*;
import by.stalmakhova.entity.Schedule;
import by.stalmakhova.exception.RecordAlreadyExist;
import by.stalmakhova.repositories.ScheduleRepository;
import by.stalmakhova.services.Interfaces.*;
import by.stalmakhova.services.PetServiceImpl;
import by.stalmakhova.services.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/schedule")
@CrossOrigin("*")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository;
    private final StatusService statusService;
    private final ProcedureService  procedureService;
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;
    private final PetService petService;
    private final MasterService masterService;
    private boolean AreSameNotes(Schedule note,Schedule schedule){
        return note.getMaster().getNameMaster().equals(schedule.getMaster().getNameMaster())
                && note.getDate().equals(schedule.getDate())
                && note.getTime().equals(schedule.getTime());
    }

    @Autowired
    public ScheduleController(ScheduleService scheduleService, ScheduleRepository scheduleRepository, StatusService statusService, ProcedureService procedureService, UserServiceImpl userService, ModelMapper modelMapper, PetServiceImpl petService, MasterService masterService) {
        this.scheduleService = scheduleService;
        this.scheduleRepository = scheduleRepository;
        this.statusService = statusService;
        this.procedureService = procedureService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.petService = petService;
        this.masterService = masterService;
    }
   @GetMapping(value="History",produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Collection<ScheduleDto>> getAllSchedule() {
      var schedule = scheduleService.getAllSchedule();
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }
    @GetMapping(value="/{login}/History",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<NoteHistory>> getHistory(@PathVariable String login){
        Long id = userService.getUserIdByLogin(login);

        if (id == null)
            return ResponseEntity.badRequest().build();

        var notesForUser = scheduleService.getAllNotesForCurrentUser(id);
        return new ResponseEntity<>(notesForUser,HttpStatus.OK);

    }
    @GetMapping (value="/aplications",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ScheduleDto>> getAplications(){
        var notes = scheduleService.getAllAplicationsWhatWaiting();
        return new ResponseEntity<>(notes,HttpStatus.OK);
    }
    @PostMapping(value="{id}/set-status",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScheduleDto> setStatus(@PathVariable Long id,@RequestBody StatusDto statusDto){
        Long status_id = statusDto.getStatus_id();
        var note = scheduleService.setNoteStatus(id,status_id);
        return new ResponseEntity<>(note,HttpStatus.OK);
    }
@GetMapping (value="",produces=MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Collection<ScheduleDto>> getAllHistory()
{
        var all=scheduleService.getAllSchedule();
    return new ResponseEntity<>(all, HttpStatus.OK);

}
    @PostMapping(value = "/{login}/Add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteOut> add(@RequestBody @Valid NoteToAdd noteToAdd, @PathVariable String login) {
        {
            Long userId = userService.getUserIdByLogin(login);
            if (userId == null)
                return ResponseEntity.badRequest().build();
            var petNickname=noteToAdd.getNickname();

            var pet = petService.getPetByNickname(petNickname);
            if (pet == null)
                return ResponseEntity.badRequest().build();
            var master = masterService.getMasterByName(noteToAdd.getMasterName());
            if (master == null)
                return ResponseEntity.badRequest().build();

            var procedure =procedureService.getProcedureTableByProcedureName(noteToAdd.getProcedureName());
            var status = statusService.getStatusById(4l);
            if (status == null)
                return ResponseEntity.badRequest().build();
            if (procedure == null)
                return ResponseEntity.badRequest().build();

            var note = scheduleService.CreateNote(userId, noteToAdd.getDate(), noteToAdd.getTime(),pet, master, procedure, status);

            scheduleRepository.findAll().forEach(noteFromServer -> {
                if (AreSameNotes(noteFromServer, note)) {
                throw new RecordAlreadyExist("This note is already exist");
                }
            });
            scheduleRepository.save(note);
            var noteOut = new NoteOut(note.getId(), note.getPet().getNickname(),note.getOwner().getLogin(), note.getDate(), note.getTime(), note.getStatus().getStatusName() ,note.getMaster().getNameMaster(),note.getProcedure().getNameProcedure());

            return new ResponseEntity<>(noteOut, HttpStatus.OK);
        }
    }
}
