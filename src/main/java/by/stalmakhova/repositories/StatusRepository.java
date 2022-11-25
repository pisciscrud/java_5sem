package by.stalmakhova.repositories;

import by.stalmakhova.entity.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository  extends CrudRepository<Status, Long> {
    Status findStatusById(Long id);
}
