package by.stalmakhova.repositories;

import by.stalmakhova.entity.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    Collection<Schedule> findScheduleByOwnerId(Long owner_id);

    Collection<Schedule> findScheduleByStatusId(Long id);
}
