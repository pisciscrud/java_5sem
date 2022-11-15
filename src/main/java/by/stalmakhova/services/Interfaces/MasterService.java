package by.stalmakhova.services.Interfaces;

import by.stalmakhova.dto.MasterDto;
import by.stalmakhova.entity.Master;

import java.util.Collection;

public interface MasterService {
    Master getMasterByName(String name);
    Collection<MasterDto> getAllMasters();
}
