package by.stalmakhova.services;

import by.stalmakhova.entity.PetType;
import by.stalmakhova.repositories.PetTypeRepository;
import by.stalmakhova.services.Interfaces.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetTypeServiceImpl implements PetTypeService {


    private final PetTypeRepository petTypeRepository;

    @Autowired
    public PetTypeServiceImpl(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public PetType findPetTypeById(Integer pet_type_id) {
        return petTypeRepository.findById(Long.valueOf(pet_type_id)).orElse(null);
    }

}
