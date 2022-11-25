package by.stalmakhova.repositories;

import by.stalmakhova.entity.Pet;
import by.stalmakhova.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {

    //найти по id владельца
    Collection<Pet> findPetByIdOwner(User user);
    Pet findPetByNickname(String nickname);
}

