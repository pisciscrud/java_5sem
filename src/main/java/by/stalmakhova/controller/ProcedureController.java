package by.stalmakhova.controller;

import by.stalmakhova.dto.ProcedureDto;
import by.stalmakhova.dto.ProcedureFromServer;
import by.stalmakhova.entity.ProcedureTable;
import by.stalmakhova.services.Interfaces.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;

@RestController
@RequestMapping("/procedures")

@CrossOrigin("*")
public class ProcedureController {
   private final ProcedureService procedureService;
    @Value("${upload.path}")
    private String uploadPath;
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
    @PostMapping(value = "CopyFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestBody MultipartFile file) {

        try {
            var inputStream = file.getInputStream();
            var path = file.getOriginalFilename();
            var destination = new File("D:\\5SEM\\Java\\project\\src\\main\\resources\\static\\images\\" + path).toPath();

            Files.copy(inputStream, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().build();
    }

}
