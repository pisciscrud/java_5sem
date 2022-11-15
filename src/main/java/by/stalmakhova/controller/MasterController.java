package by.stalmakhova.controller;

import by.stalmakhova.dto.MasterDto;
import by.stalmakhova.entity.Master;
import by.stalmakhova.services.Interfaces.MasterService;
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
    @Autowired
    public MasterController(MasterService masterService) {
        this.masterService = masterService;
    }



    @GetMapping(value="",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<MasterDto>> getAllMasters() {
        var masters = masterService.getAllMasters();
        return new ResponseEntity<>(masters, HttpStatus.OK);
    }




    @GetMapping(value = "/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Master> getMaster(@PathVariable String name) {

        var master = masterService.getMasterByName(name);
        return new ResponseEntity<>(master, HttpStatus.OK);
    }

}
