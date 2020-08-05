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

    @PutMapping("/{gid}/{ufid}")
    public void updateUrenFormulierToGebruiker(@PathVariable(value = "ufid") long ufid, @PathVariable(value = "gid") long gid) {
        gs.addUrenFormulierToGebruiker(gid, ufid);
    }

    @GetMapping("/{gid}/{ufid}/{wdid}")
    public Werkdag getWerkdagGebruiker(@PathVariable(value = "gid") long gid, @PathVariable(value = "ufid") long ufid, @PathVariable(value = "wdid") long wdid){
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
}
