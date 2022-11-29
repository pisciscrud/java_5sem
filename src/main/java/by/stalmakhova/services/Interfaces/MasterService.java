package by.stalmakhova.services.Interfaces;

import by.stalmakhova.dto.MasterDto;
import by.stalmakhova.entity.Master;
import by.stalmakhova.entity.ProcedureTable;

import java.util.Collection;

public interface MasterService {
    Master getMasterByName(String name);
    Collection<MasterDto> getAllMasters();

    MasterDto CreateMaster(String nameMaster, ProcedureTable procedure);

    void deleteById(Long id);
}
