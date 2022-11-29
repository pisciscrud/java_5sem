package by.stalmakhova.controller;

import by.stalmakhova.dto.PetDto;
import by.stalmakhova.dto.PetOut;
import by.stalmakhova.dto.PetToAdd;
import by.stalmakhova.repositories.UserRepository;
import by.stalmakhova.services.Interfaces.PetService;
import by.stalmakhova.services.Interfaces.PetTypeService;
import by.stalmakhova.services.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/pets")
@CrossOrigin("*")
public class PetController {
    private final PetService petService;
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;
    private final PetTypeService petTypeService;
    private final UserRepository userRepository;

    @Autowired
    public PetController(PetService petService, ModelMapper modelMapper, UserServiceImpl userService, PetTypeService petTypeService,UserRepository userRepository) {
        this.petService = petService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.petTypeService = petTypeService;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<PetDto>> getAllPets() {
        var pets = petService.getAllPets();
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @GetMapping(value = "/PetForUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<PetOut>> getUserPets( Model model) {

        final var currentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if (null == currentUserDetails)
            throw new BadCredentialsException("Not authorized");
        final var userId = this.userRepository.findByLogin(currentUserDetails.getUsername()).get().getId();

        if (userId == null)
            return ResponseEntity.badRequest().build();

        var pets = petService.getAllPetsForCurrentUser(userId);
        model.addAttribute("pets", pets);
        return new ResponseEntity<>(pets, HttpStatus.OK);

    }

    @PostMapping(value = "/AddPet", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PetOut> addPet(@RequestBody @Valid PetToAdd petToAdd) {
        {
            final var currentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            if (null == currentUserDetails)
                throw new BadCredentialsException("Not authorized");
            final var userId = this.userRepository.findByLogin(currentUserDetails.getUsername()).get().getId();
            var petTypeId = petToAdd.getPet_type_id();
            var petType = petTypeService.findPetTypeById(Math.toIntExact(petTypeId));

            var pet = petService.CreatePet(userId, petType, petToAdd.getAge(),petToAdd.getNickname());
            var petOut = new PetOut(pet.getId(), pet.getAge(),pet.getNickname(), pet.getPetType().getPetName(), pet.getIdOwner().getId());
            return new ResponseEntity<>(petOut, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/DeletePet/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletePet( @PathVariable Long id) {
        final var currentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if (null == currentUserDetails)
            throw new BadCredentialsException("Not authorized");
        final var userId = this.userRepository.findByLogin(currentUserDetails.getUsername()).get().getId();
        if (userId == null)
            return ResponseEntity.badRequest().build();

        petService.deleteById(id);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
}




