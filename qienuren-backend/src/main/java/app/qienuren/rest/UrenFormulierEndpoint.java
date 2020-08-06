package app.qienuren.rest;

import app.qienuren.controller.UrenFormulierService;
import app.qienuren.controller.WerkdagService;
import app.qienuren.model.UrenFormulier;
import app.qienuren.model.Werkdag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urenformulier")
public class UrenFormulierEndpoint {
    @Autowired
    UrenFormulierService ufs;

    @GetMapping("/get")
    public Iterable<UrenFormulier> getUrenformulieren(){
        return ufs.getAllUrenFormulieren();
    }

    @PostMapping("/new")
    public UrenFormulier addNewUrenFormulier(@RequestBody UrenFormulier urenFormulier) {
        return ufs.addNewUrenFormulier(urenFormulier);
    }

    @PutMapping("/{urenformulierid}/{werkdagid}")
    public Object updateWorkDaytoUrenFormulier(@PathVariable(value = "urenformulierid") long ufid, @PathVariable(value = "werkdagid") long wdid) {
      return ufs.addWorkDaytoUrenFormulier(ufid, wdid);
    }

    @GetMapping("/gewerkteuren/{id}")
    public double getTotaalGewerkteUren(@PathVariable(value = "id") long id) {
        return ufs.getTotaalGewerkteUren(id);
    }

    @GetMapping("/permaand/{maandid}")
    public Iterable<UrenFormulier> getUrenFormulierPerMaand(@PathVariable(value = "maandid") int maandid) {
        return ufs.getUrenFormulierPerMaand(maandid);
    }
}
