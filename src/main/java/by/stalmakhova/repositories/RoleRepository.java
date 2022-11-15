package by.stalmakhova.repositories;

import by.stalmakhova.entity.RoleTable;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleTable,Long> {
    //Optional<Role> findByRole(String roleName);


}
