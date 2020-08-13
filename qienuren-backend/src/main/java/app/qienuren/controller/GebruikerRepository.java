package app.qienuren.controller;

import app.qienuren.model.Gebruiker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface GebruikerRepository extends CrudRepository<Gebruiker, Long> {

}
