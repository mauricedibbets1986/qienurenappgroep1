package app.qienuren.rest;

import app.qienuren.controller.GebruikerService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
