package by.stalmakhova.repositories;

import by.stalmakhova.entity.Pet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {
}

