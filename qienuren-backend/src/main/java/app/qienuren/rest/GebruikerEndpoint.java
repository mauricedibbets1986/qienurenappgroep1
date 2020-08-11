package app.qienuren.rest;

import app.qienuren.controller.GebruikerRepository;
import app.qienuren.controller.GebruikerService;
import app.qienuren.model.Gebruiker;
import app.qienuren.model.Trainee;
import app.qienuren.model.Werkdag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gebruiker")
public class GebruikerEndpoint {
    @Autowired
    GebruikerService gs;

    @Autowired
    GebruikerRepository gr;

    @PutMapping("/{gebruikerid}/{urenformulierid}")
    public void updateUrenFormulierToGebruiker(@PathVariable(value = "urenformulierid") long ufid, @PathVariable(value = "gebruikerid") long gid) {
        gs.addUrenFormulierToGebruiker(gid, ufid);
    }

    @GetMapping("/{gebruikerid}/{urenformulierid}/{werkdagid}")
    public Werkdag getWerkdagGebruiker(@PathVariable(value = "gebruikerid") long gid, @PathVariable(value = "urenformulierid") long ufid, @PathVariable(value = "werkdagid") long wdid){
        Werkdag wd = gs.getWerkdagGebruiker(gid, ufid, wdid);
        return wd;
    }

    @GetMapping("/{id}")
    public Gebruiker getGebruikerById(@PathVariable(value = "id") long id) {
    	System.out.println("endpoint called");
        return gs.getGebruikerById(id);
    }

    @PutMapping("/changedetails/{id}")
    public void changeDetailsById(@PathVariable(value = "id") long id, @RequestBody Gebruiker gebruiker) {
        gs.changeDetails(gr.findById(id).get(), gebruiker);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteGebruikerById(@PathVariable(value = "id") long id){
        gs.deleteGebruikerById(id);
        return "Gebruiker met id " + id + " verwijderd";
    }
}
