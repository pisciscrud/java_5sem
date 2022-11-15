package by.stalmakhova.controller;
import by.stalmakhova.dto.ProcedureFromServer;
import by.stalmakhova.entity.ProcedureTable;
import by.stalmakhova.services.Interfaces.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/procedures")

@CrossOrigin("*")
public class ProcedureController {
   private final ProcedureService procedureService;
@Autowired
    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @GetMapping(value="",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ProcedureFromServer>> getAllProcedures() {

        var procedures = procedureService.getAllProcedures();
        return new ResponseEntity<>(procedures, HttpStatus.OK);
    }
    @GetMapping(value = "/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProcedureTable> getProcedure(@PathVariable String name) {

        var procedure = procedureService.getProcedureByName(name);
        return new ResponseEntity<>(procedure, HttpStatus.OK);
    }
}
