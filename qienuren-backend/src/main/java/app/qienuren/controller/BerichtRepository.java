package app.qienuren.controller;

import app.qienuren.model.Bericht;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface BerichtRepository extends CrudRepository<Bericht, Long> {
}
