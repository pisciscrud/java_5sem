package by.stalmakhova.services.Interfaces;

import by.stalmakhova.dto.PetDto;
import by.stalmakhova.dto.PetOut;
import by.stalmakhova.entity.Pet;
import by.stalmakhova.entity.PetType;

import java.util.Collection;

public interface PetService {
    Collection<PetDto> getAllPets();
    void deleteById(Long id);
    Pet getPetByNickname(String nickname);
    Pet CreatePet(Long petDto, PetType id, Integer age, String nickname);
    Collection<PetOut> getAllPetsForCurrentUser(Long id);


}

