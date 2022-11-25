package by.stalmakhova.repositories;
import by.stalmakhova.entity.ProcedureTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedureRepository  extends  CrudRepository<ProcedureTable, Long> {
    ProcedureTable findProcedureTableByNameProcedure(String nameProcedure);
    ProcedureTable findProcedureTableById(Long id);

}
