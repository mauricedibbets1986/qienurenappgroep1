package app.qienuren.rest;

import app.qienuren.controller.GebruikerRepository;
import app.qienuren.controller.GebruikerService;
import app.qienuren.model.Gebruiker;
import app.qienuren.model.UrenFormulier;
import app.qienuren.model.Werkdag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gebruiker")
public class GebruikerEndpoint {
    @Autowired
    GebruikerService gebruikerService;

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
    public Iterable<Gebruiker> getTrainees(@PathVariable(value = "role") String role){
        return gebruikerService.getByRole(role);
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
}
