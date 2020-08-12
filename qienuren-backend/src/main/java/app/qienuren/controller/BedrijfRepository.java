package app.qienuren.controller;

import app.qienuren.model.Bedrijf;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface BedrijfRepository extends CrudRepository<Bedrijf, Long> {
}
