package by.stalmakhova.services.Interfaces;

import by.stalmakhova.dto.ProcedureFromServer;
import by.stalmakhova.entity.ProcedureTable;


import java.util.Collection;

public interface ProcedureService {
    ProcedureTable getProcedureByName(String name);
    Collection<ProcedureFromServer> getAllProcedures();
}
