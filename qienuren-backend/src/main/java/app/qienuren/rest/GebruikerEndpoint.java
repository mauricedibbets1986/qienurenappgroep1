package app.qienuren.rest;

import app.qienuren.controller.GebruikerRepository;
import app.qienuren.controller.GebruikerService;
import app.qienuren.controller.UrenFormulierService;
import app.qienuren.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/gebruiker")
public class GebruikerEndpoint {
    @Autowired
    GebruikerService gebruikerService;

    @Autowired
    UrenFormulierService urenFormulierService;

    @Autowired
    GebruikerRepository gebruikerRepository;

    @GetMapping("/{gebruikerid}/{urenformulierid}/{werkdagid}")
    public Werkdag getWerkdagGebruiker(@PathVariable(value = "gebruikerid") long gid, @PathVariable(value = "urenformulierid") long ufid, @PathVariable(value = "werkdagid") long wdid){
        Werkdag wd = gebruikerService.getWerkdagGebruiker(gid, ufid, wdid);
        return wd;
    }


    @GetMapping("/{id}")
    public Gebruiker getGebruikerById(@PathVariable(value = "id") long id) {
    	System.out.println("endpoint called");
        return gebruikerService.getGebruikerById(id);
    }

    @GetMapping("/rol-id/{id}")
    public Object getGebruikerrolById(@PathVariable(value = "id") long id) {
        return gebruikerService.getGebruikerById(id).getClass();
    }

    @GetMapping("/rol/{role}")
    public Iterable<Gebruiker> getGebruikerByRole(@PathVariable(value = "role") Role role){
        return gebruikerService.getByRole(role);
    }

    @GetMapping("/voornaam/{voornaam}")
    public Iterable<Gebruiker> getGebruikerByVoornaam(@PathVariable(value = "voornaam") String voornaam){
        return gebruikerService.getByVoornaam(voornaam);
    }

    @GetMapping("/achternaam/{achternaam}")
    public Iterable<Gebruiker> getGebruikerByAchternaam(@PathVariable(value = "achternaam") String achternaam){
        return gebruikerService.getByAchternaam(achternaam);
    }

    @GetMapping("/woonplaats/{woonplaats}")
    public Iterable<Gebruiker> getGebruikerByWoonplaats(@PathVariable(value = "woonplaats") String woonplaats){
        return gebruikerService.getByWoonplaats(woonplaats);
    }

    @GetMapping("/geboorteDatum/{geboorteDatum}")
    public Iterable<Gebruiker> getGebruikerByGeboorteDatum(@PathVariable(value = "geboorteDatum") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate geboorteDatum){
        return gebruikerService.getByGeboorteDatum(geboorteDatum);
    }

    @GetMapping("/email/{emailadres}")
    public Iterable<Gebruiker> getEmail(@PathVariable(value = "emailadres") String emailadres){
        return gebruikerService.getByEmail(emailadres);
    }
    @GetMapping("/all")
    public Iterable<Gebruiker> getAll() {
        return gebruikerService.getAllUsers();
    }

    //@PutMapping("/changedetails/{id}")
    //public void changeDetailsById(@PathVariable(value = "id") long id, @RequestBody Gebruiker gebruiker) {
       // gebruikerService.changeDetails(gebruikerRepository.findById(id).get(), gebruiker);
   // }

    @PutMapping("/{gebruikerid}/{urenformulierid}")
    public void updateUrenFormulierToGebruiker(@PathVariable(value = "urenformulierid") long ufid, @PathVariable(value = "gebruikerid") long gid) {
        gebruikerService.addUrenFormulierToGebruiker(gid, ufid);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteGebruikerById(@PathVariable(value = "id") long id){
        gebruikerService.deleteGebruikerById(id);
        return "Gebruiker met id " + id + " verwijderd";
    }

    @GetMapping("/{id}/urenformulieren")
    public Iterable<UrenFormulier> getUrenformulierenByGebruiker(@PathVariable(value = "id") long id) {
        return gebruikerService.getUrenformulierenVanGebruiker(id);
    }

    @GetMapping("/gewerkteuren/{id}")
    public double getTotaalGewerkteUren(@PathVariable(value = "id") long id) {
        return urenFormulierService.getGewerkteUrenByID(id);
    }

    @PutMapping("/{id}/setstatus-checkgebruiker")
    public UrenFormulier setStatusFormulierCheckGebruiker(@PathVariable(value = "id") long id) {
        urenFormulierService.getUrenFormulierById(id).setStatusGoedkeuring(StatusGoedkeuring.INGEDIEND_GEBRUIKER);
        return urenFormulierService.getUrenFormulierById(id);
    }

    }

