package by.stalmakhova.controller;

import by.stalmakhova.dto.MasterAdd;
import by.stalmakhova.dto.MasterDto;
import by.stalmakhova.entity.Master;
import by.stalmakhova.services.Interfaces.MasterService;
import by.stalmakhova.services.Interfaces.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/masters")

@CrossOrigin("*")
public class MasterController {
    private final MasterService masterService;
    private final ProcedureService procedureService;
    @Autowired
    public MasterController(MasterService masterService,ProcedureService procedureService) {
        this.masterService = masterService;
        this.procedureService=procedureService;
    }



    @GetMapping(value="",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<MasterDto>> getAllMasters() {
        var masters = masterService.getAllMasters();
        return new ResponseEntity<>(masters, HttpStatus.OK);
    }

   @PostMapping(value="/add",produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<MasterDto> addMaster(@RequestBody MasterAdd masterAdd)
   {

       var procedure =procedureService.getProcedureById(masterAdd.getId_procedure());

       var master =masterService.CreateMaster(masterAdd.getNameMaster(),procedure);

       return new ResponseEntity<>(master,HttpStatus.OK);
   }


    @GetMapping(value = "/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Master> getMaster(@PathVariable String name) {

        var master = masterService.getMasterByName(name);
        return new ResponseEntity<>(master, HttpStatus.OK);
    }

}
