package by.stalmakhova.controller;

import by.stalmakhova.dto.PetDto;
import by.stalmakhova.services.Interfaces.PetService;
import by.stalmakhova.services.PetServiceImpl;
import by.stalmakhova.services.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/pets")
@CrossOrigin("*")
public class PetControler {
    private final PetService petService;
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;
    @Autowired
    public PetControler(PetService petService, ModelMapper modelMapper,UserServiceImpl userService) {
        this.petService = petService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping(value="",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<PetDto>> getAllPets() {
        var pets = petService.getAllPets();
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }
    @GetMapping(value = "PetForUser/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<PetDto>> getUserRatedTracks(@PathVariable String login) {

        Long id = userService.getUserIdByLogin(login);

        if (id == null)
            return ResponseEntity.badRequest().build();

        var pets = petService.getAllPetsForCurrentUser(id);

        return new ResponseEntity<>(pets, HttpStatus.OK);

    }
}
