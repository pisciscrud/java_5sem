package by.stalmakhova.services;


import by.stalmakhova.dto.ScheduleDto;
import by.stalmakhova.repositories.ScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ScheduleService {
    private final ModelMapper modelMapper;
    public final ScheduleRepository scheduleRepository;
    @Autowired
    public ScheduleService(ModelMapper modelMapper, ScheduleRepository scheduleRepository) {
        this.modelMapper = modelMapper;
        this.scheduleRepository = scheduleRepository;
    }

    public Collection<ScheduleDto> getAllSchedule() {
        var schedules = scheduleRepository.findAll();

        var schedulesDto = new ArrayList<ScheduleDto>();

        for (var schedule : schedules) {
            var scheduleDto = this.modelMapper.map(schedule, ScheduleDto.class);
            schedulesDto.add(scheduleDto);
        }

        return schedulesDto;

    }
}
