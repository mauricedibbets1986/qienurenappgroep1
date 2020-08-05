package app.qienuren.rest;

import app.qienuren.controller.GebruikerService;
import app.qienuren.model.Werkdag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GebruikerEndpoint {
    @Autowired
    GebruikerService gs;

    @PostMapping("/{gid}/{ufid}")
    public void updateWorkDaytoUrenFormulier(@PathVariable(value = "ufid") long ufid, @PathVariable(value = "gid") long gid) {
        gs.addUrenFormulierToGebruiker(gid, ufid);
    }

    @GetMapping("/{gid}/{ufid}/{wdid}")
    public Werkdag getWerkdagGebruiker(@PathVariable(value = "gid") long gid, @PathVariable(value = "ufid") long ufid, @PathVariable(value = "wdid") long wdid){
        Werkdag wd = gs.getWerkdagGebruiker(gid, ufid, wdid);
        return wd;
    }
}
