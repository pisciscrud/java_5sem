package by.stalmakhova.services.Interfaces;

import by.stalmakhova.dto.ProcedureDto;
import by.stalmakhova.dto.ProcedureFromServer;
import by.stalmakhova.entity.ProcedureTable;

import java.util.Collection;

public interface ProcedureService {
   // ProcedureTable getProcedureByName(String name);
    Collection<ProcedureFromServer> getAllProcedures();
 Collection<ProcedureFromServer> getAllProceduresSorted( Long num);
     ProcedureTable getProcedureById(Long id);
     ProcedureFromServer getProcedureByProcedureName(String procedureName);
      ProcedureTable getProcedureTableByProcedureName(String procedureName);

    ProcedureDto CreateProcedure(String name_procedure, double price, String photo);


    void deleteProcedureById(Long id);


}
