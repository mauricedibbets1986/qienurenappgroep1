package app.qienuren.controller;

import app.qienuren.model.Trainee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface TraineeRepository extends CrudRepository<Trainee, Long> {
}
