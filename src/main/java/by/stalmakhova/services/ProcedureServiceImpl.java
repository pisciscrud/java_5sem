package by.stalmakhova.services;

import by.stalmakhova.dto.ProcedureDto;
import by.stalmakhova.dto.ProcedureFromServer;
import by.stalmakhova.entity.ProcedureTable;
import by.stalmakhova.repositories.ProcedureRepository;
import by.stalmakhova.services.Interfaces.ProcedureService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class ProcedureServiceImpl  implements ProcedureService {
    private final ModelMapper modelMapper;
    public final ProcedureRepository procedureRepository;
@Autowired
    public ProcedureServiceImpl(ModelMapper modelMapper, ProcedureRepository procedureRepository) {
    this.modelMapper = modelMapper;
    this.procedureRepository = procedureRepository;
    }

    public  Collection<ProcedureFromServer> getAllProcedures() {
        var procedures = procedureRepository.findAll();

        var proceduresFromServer = new ArrayList<ProcedureFromServer>();

        for (var procedure : procedures) {
            var procedureFromServer = this.modelMapper.map(procedure, ProcedureFromServer.class);
            proceduresFromServer.add(procedureFromServer);
        }

        return proceduresFromServer;

    }
    @Override
    public ProcedureTable getProcedureTableByProcedureName(String name) {
        return   procedureRepository.findProcedureTableByNameProcedure(name);

    }

    public Iterable<ProcedureTable> findAll() {

        return  procedureRepository.findAll();
    }
  @Override
    public void deleteProcedureById(Long id) {
        procedureRepository.deleteById(id);
    }
    @Override
    public Collection<ProcedureFromServer> getAllProceduresSorted(Long num) {
        var proceduresFromServer = new ArrayList<ProcedureFromServer>();
        if (num == 1) {
            var procedures = procedureRepository.findByOrderByPriceAsc();
            for (var procedure : procedures) {
                var procedureFromServer = this.modelMapper.map(procedure, ProcedureFromServer.class);
                proceduresFromServer.add(procedureFromServer);
            }
        } else {
            var procedures = procedureRepository.findByOrderByPriceDesc();
            for (var procedure : procedures) {
                var procedureFromServer = this.modelMapper.map(procedure, ProcedureFromServer.class);
                proceduresFromServer.add(procedureFromServer);
            }
        }
        return proceduresFromServer;
    }

    @Override
    public ProcedureTable getProcedureById(Long id){
   return  procedureRepository.findProcedureTableById(id);
    }
@Override
    public ProcedureDto CreateProcedure(String name_procedure, double price, String photo)
    {
        var procedure=new ProcedureTable();
        procedure.setNameProcedure(name_procedure);
        procedure.setPrice(price);
        procedure.setPhoto(photo);
        procedureRepository.save(procedure);

        return  modelMapper.map(procedure,ProcedureDto.class);
    }
}
