package by.stalmakhova.services;

import by.stalmakhova.entity.Status;
import by.stalmakhova.repositories.StatusRepository;
import by.stalmakhova.services.Interfaces.StatusService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {
    private final ModelMapper modelMapper;
    private final StatusRepository statusRepository;
    public StatusServiceImpl(ModelMapper modelMapper, StatusRepository statusRepository) {
        this.modelMapper = modelMapper;
        this.statusRepository = statusRepository;
    }
    @Override
    public Status getStatusById(Long id) {
        return statusRepository.findStatusById(id);
    }
}

