package by.stalmakhova.controller;

import by.stalmakhova.dto.ProcedureDto;
import by.stalmakhova.dto.ProcedureFromServer;
import by.stalmakhova.entity.ProcedureTable;
import by.stalmakhova.services.Interfaces.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        var procedure = procedureService.getProcedureTableByProcedureName(name);
        return new ResponseEntity<>(procedure, HttpStatus.OK);
    }
    @PostMapping(value="/add",produces = MediaType.APPLICATION_JSON_VALUE)
            public ResponseEntity<ProcedureDto> addProcedure(@RequestBody ProcedureFromServer procedureFromServer)
    {

        var proceduretoAdd= procedureService.CreateProcedure(procedureFromServer.getNameProcedure(),procedureFromServer.getPrice(),procedureFromServer.getPhoto());
        return new ResponseEntity<>(proceduretoAdd, HttpStatus.OK);

    }

}
