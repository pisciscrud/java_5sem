package by.stalmakhova.services;

import by.stalmakhova.dto.MasterDto;
import by.stalmakhova.entity.Master;
import by.stalmakhova.entity.ProcedureTable;
import by.stalmakhova.repositories.MasterRepository;
import by.stalmakhova.services.Interfaces.MasterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class MasterServiceImpl implements MasterService {
    private final ModelMapper modelMapper;
    private final MasterRepository masterRepository;
    @Autowired
    public MasterServiceImpl(ModelMapper modelMapper, MasterRepository masterRepository) {
        this.modelMapper = modelMapper;
        this.masterRepository = masterRepository;
    }
    @Override
    public void deleteById(Long id) {
        masterRepository.deleteById(id);
    }
    @Override
    public Collection<MasterDto> getAllMasters() {
        var masters = masterRepository.findAll();
        var mastersFromServer = new ArrayList<MasterDto>();
        for (var master : masters) {
            var masterFromServer = this.modelMapper.map(master, MasterDto.class);
            mastersFromServer.add(masterFromServer);
        }
        return mastersFromServer;
    }
    @Override
    public MasterDto CreateMaster(String nameMaster, ProcedureTable procedure)
    {
     var master = new Master();
     master.setNameMaster(nameMaster);
     master.setIdProcedure(procedure);
      masterRepository.save(master);

     return  modelMapper.map(master,MasterDto.class);
    }
   @Override
    public Master getMasterByName(String name) {
        var master = masterRepository.findMasterByNameMaster(name);
        return master;
    }
}
