package app.qienuren.rest;

import app.qienuren.controller.UrenFormulierService;
import app.qienuren.model.StatusGoedkeuring;
import app.qienuren.model.UrenFormulier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urenformulier")
public class UrenFormulierEndpoint {
    @Autowired
    UrenFormulierService urenFormulierService;


    @GetMapping("/get")
    public Iterable<UrenFormulier> getUrenformulieren(){
        return urenFormulierService.getAllUrenFormulieren();
    }

    @PostMapping("/new")
    public UrenFormulier addNewUrenFormulier(@RequestBody UrenFormulier urenFormulier) {
        return urenFormulierService.addNewUrenFormulier(urenFormulier);
    }

    @PutMapping("/{urenformulierid}/{werkdagid}")
    public Object updateWorkDaytoUrenFormulier(@PathVariable(value = "urenformulierid") long ufid, @PathVariable(value = "werkdagid") long wdid) {
      return urenFormulierService.addWorkDaytoUrenFormulier(ufid, wdid);
    }

    @GetMapping("/gewerkteuren/{id}")
    public double getTotaalGewerkteUren(@PathVariable(value = "id") long id) {
        return urenFormulierService.getTotaalGewerkteUren(id);
    }

    @GetMapping("/permaand/{maandid}")
    public Iterable<UrenFormulier> getUrenFormulierPerMaand(@PathVariable(value = "maandid") int maandid) {
        return urenFormulierService.getUrenFormulierPerMaand(maandid);
    }

    @PutMapping("/{id}/setstatus-checkgebruiker")
    public UrenFormulier setStatusFormulierCheckGebruiker(@PathVariable(value = "id") long id) {
        urenFormulierService.getUrenFormulierById(id).setStatusGoedkeuring(StatusGoedkeuring.CHECKGEBRUIKER);
        return urenFormulierService.getUrenFormulierById(id);
    }

    @GetMapping("/{id}")
    public UrenFormulier getUrenFormulierById(@PathVariable(value = "id") long id) {
        System.out.println("endpoint called");
        return urenFormulierService.getUrenFormulierById(id);
    }

}
