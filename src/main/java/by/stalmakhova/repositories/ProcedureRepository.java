package by.stalmakhova.repositories;

import by.stalmakhova.entity.ProcedureTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProcedureRepository  extends JpaRepository<ProcedureTable, Long> {

   ProcedureTable findProcedureTableByNameProcedure(String nameProcedure);
    ProcedureTable findProcedureTableById(Long id);

    Collection<ProcedureTable> findByOrderByPriceAsc();
    Collection<ProcedureTable> findByOrderByPriceDesc();


}
