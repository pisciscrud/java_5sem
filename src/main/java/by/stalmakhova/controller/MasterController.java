package by.stalmakhova.controller;

import by.stalmakhova.dto.MasterAdd;
import by.stalmakhova.dto.MasterDto;
import by.stalmakhova.entity.Master;
import by.stalmakhova.repositories.UserRepository;
import by.stalmakhova.services.Interfaces.MasterService;
import by.stalmakhova.services.Interfaces.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/masters")

@CrossOrigin("*")
public class MasterController {
    private final MasterService masterService;
    private final ProcedureService procedureService;
    private UserRepository userRepository;

    @Autowired
    public MasterController(MasterService masterService,ProcedureService procedureService,UserRepository userRepository) {
        this.masterService = masterService;
        this.procedureService=procedureService;
        this.userRepository = userRepository;
    }


      @PreAuthorize("hasAnyRole('ADMIN_ROLE','USER_ROLE')")
    @GetMapping(value="",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<MasterDto>> getAllMasters() {
        var masters = masterService.getAllMasters();
        return new ResponseEntity<>(masters, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN_ROLE')")
   @PostMapping(value="/add",produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<MasterDto> addMaster(@RequestBody MasterAdd masterAdd)
   {

       var procedure =procedureService.getProcedureById(masterAdd.getId_procedure());

       var master =masterService.CreateMaster(masterAdd.getNameMaster(),procedure);

       return new ResponseEntity<>(master,HttpStatus.OK);
   }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletePet( @PathVariable Long id) {
        final var currentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if (null == currentUserDetails)
            throw new BadCredentialsException("Not authorized");
        final var userId = this.userRepository.findByLogin(currentUserDetails.getUsername()).get().getId();
        if (userId == null)
            return ResponseEntity.badRequest().build();

        masterService.deleteById(id);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }


    @GetMapping(value = "/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Master> getMaster(@PathVariable String name) {

        var master = masterService.getMasterByName(name);
        return new ResponseEntity<>(master, HttpStatus.OK);
    }

}
