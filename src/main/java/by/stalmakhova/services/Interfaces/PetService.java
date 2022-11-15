package by.stalmakhova.services.Interfaces;

import by.stalmakhova.dto.PetDto;
import by.stalmakhova.entity.Pet;

import java.util.Collection;

public interface PetService {
    Collection<PetDto> getAllPets();

    Collection<PetDto> getAllPetsForCurrentUser(Long id);
}

