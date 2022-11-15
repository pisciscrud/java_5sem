package by.stalmakhova.repositories;

import by.stalmakhova.entity.Master;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends CrudRepository<Master, Long> {
    Master findMasterByNameMaster(String nameMaster);
}
