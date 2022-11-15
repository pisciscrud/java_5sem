package by.stalmakhova.services;

import by.stalmakhova.dto.PetDto;
import by.stalmakhova.entity.Pet;
import by.stalmakhova.repositories.PetRepository;
import by.stalmakhova.repositories.UserRepository;
import by.stalmakhova.services.Interfaces.PetService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class PetServiceImpl implements PetService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    public final PetRepository petRepository;
    public PetServiceImpl(ModelMapper modelMapper, PetRepository petRepository,UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.petRepository = petRepository;
        this.userRepository=userRepository;
    }
 @Override
    public Collection<PetDto> getAllPets() {
        var pets = petRepository.findAll();
        var petsDto= new ArrayList<PetDto>();
     this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
        for (var pet : pets) {
            var petDto = this.modelMapper.map(pet, PetDto.class);
            petsDto.add(petDto);

        }
        return petsDto;
    }

    @Override
    public Collection<PetDto> getAllPetsForCurrentUser(Long id) {

        var currentUser = userRepository.findById(id).orElse(null);

        if (currentUser == null)
            throw new IllegalArgumentException("User with id " + id + " not found");

        var allPets = new ArrayList<PetDto>();
        petRepository.findAll().forEach(pet -> allPets.add(modelMapper.map(pet, PetDto.class)));
        
        return allPets;
    }
}
