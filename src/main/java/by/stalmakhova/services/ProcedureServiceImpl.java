package by.stalmakhova.services;

import by.stalmakhova.dto.ProcedureFromServer;
import by.stalmakhova.entity.ProcedureTable;
import by.stalmakhova.repositories.ProcedureRepository;
import by.stalmakhova.services.Interfaces.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
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
    public ProcedureTable getProcedureByName(String name) {
        var procedure = procedureRepository.findProcedureByNameProcedure(name);
        return procedure;
    }

    public Iterable<ProcedureTable> findAll() {

        return  procedureRepository.findAll();
    }

}
