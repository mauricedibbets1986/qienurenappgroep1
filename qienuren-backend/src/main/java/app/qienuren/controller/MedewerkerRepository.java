package app.qienuren.controller;

import app.qienuren.model.Medewerker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface MedewerkerRepository extends CrudRepository<Medewerker, Long> {
}
