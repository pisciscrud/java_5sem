package by.stalmakhova.controller;

import by.stalmakhova.dto.ProcedureDto;
import by.stalmakhova.dto.ProcedureFromServer;
import by.stalmakhova.entity.ProcedureTable;
import by.stalmakhova.services.Interfaces.ProcedureService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private ModelMapper modelMapper;

    @Autowired
    public ProcedureController(ProcedureService procedureService,ModelMapper modelMapper) {
        this.procedureService = procedureService;
        this.modelMapper=modelMapper;
    }

    @GetMapping(value="",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ProcedureFromServer>> getAllProcedures() {

        var procedures = procedureService.getAllProcedures();
        return new ResponseEntity<>(procedures, HttpStatus.OK);
    }
    @GetMapping(value="/sort/{id}",produces = MediaType.APPLICATION_JSON_VALUE)

        public ResponseEntity<Collection<ProcedureFromServer>> sortAllProcedures(@PathVariable Long id){
        var procedure = procedureService.getAllProceduresSorted(id);
        return new ResponseEntity<>(procedure, HttpStatus.OK);

    }
    @GetMapping(value="/search/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProcedureFromServer> searchProcedureByName(@PathVariable String name){
        var procedure = procedureService.getProcedureByProcedureName(name);
        if (procedure == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(procedure, HttpStatus.OK);
    }

    @GetMapping(value = ".html/id={id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProcedureTable> getProcedure(@PathVariable Long id ) {

        var procedure = procedureService.getProcedureById(id);
        return new ResponseEntity<>(procedure, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value="/add",produces = MediaType.APPLICATION_JSON_VALUE)
            public ResponseEntity<ProcedureDto> addProcedure(@RequestBody ProcedureFromServer procedureFromServer)
    {

        var proceduretoAdd= procedureService.CreateProcedure(procedureFromServer.getNameProcedure(),procedureFromServer.getPrice(),procedureFromServer.getPhoto());
        return new ResponseEntity<>(proceduretoAdd, HttpStatus.OK);

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @DeleteMapping(value = "/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteProcedure(@PathVariable Long id) {
      procedureService.deleteProcedureById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
