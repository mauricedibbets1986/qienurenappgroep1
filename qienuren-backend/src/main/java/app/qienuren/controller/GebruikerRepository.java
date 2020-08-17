package app.qienuren.controller;

import app.qienuren.model.Gebruiker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface GebruikerRepository extends CrudRepository<Gebruiker, Long> {

   //Iterable<Gebruiker> findByRole(String role);

   @Query(value = "FROM Gebruiker WHERE role=?1" )
   Iterable<Gebruiker> findByRole(String role);

   @Query(value = "FROM Gebruiker WHERE emailadres=?1 ORDER BY naam ASC" )
   Iterable<Gebruiker> findByEmail(String emailadres);
}
