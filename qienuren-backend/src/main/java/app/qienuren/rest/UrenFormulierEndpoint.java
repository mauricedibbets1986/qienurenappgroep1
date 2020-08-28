package app.qienuren.rest;

import app.qienuren.controller.GebruikerService;
import app.qienuren.controller.UrenFormulierService;
import app.qienuren.model.StatusGoedkeuring;
import app.qienuren.model.UrenFormulier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urenformulier")
public class UrenFormulierEndpoint {
    @Autowired
    UrenFormulierService urenFormulierService;

    @Autowired
    GebruikerService gebruikerService;

    String huidigeRol;


    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @GetMapping("/get")
    public Iterable<UrenFormulier> getUrenformulieren() {
        return urenFormulierService.getAllUrenFormulieren();
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @PostMapping("/new")
    public UrenFormulier addNewUrenFormulier(@RequestBody UrenFormulier urenFormulier) {
        return urenFormulierService.addNewUrenFormulier(urenFormulier);
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @PutMapping("/{urenformulierid}/{werkdagid}")
    public Object updateWorkDaytoUrenFormulier(@PathVariable(value = "urenformulierid") long ufid, @PathVariable(value = "werkdagid") long wdid) {
        return urenFormulierService.addWorkDaytoUrenFormulier(ufid, wdid);
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @GetMapping("/gewerkteuren/{id}")
    public double getTotaalGewerkteUren(@PathVariable(value = "id") long id) {
        return urenFormulierService.getTotaalGewerkteUren(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @GetMapping("/permaand/{maandid}")
    public Iterable<UrenFormulier> getUrenFormulierPerMaand(@PathVariable(value = "maandid") int maandid) {
        return urenFormulierService.getUrenFormulierPerMaand(maandid);
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @GetMapping("/{id}")
    public UrenFormulier getUrenFormulierById(@PathVariable(value = "id") long id) {
        System.out.println("endpoint called");
        return urenFormulierService.getUrenFormulierById(id);
    }


    //goedkeuringen endpoints gebruiker_ingediend, admin_ingediend, bedrijf_ingediend
    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @PutMapping("/gebruiker/{urenformulierid}/setstatus-indienengebruiker")

    public UrenFormulier setStatusFormulierIngediendGebruiker(@PathVariable(value = "urenformulierid") long urenformulierid) {
        urenFormulierService.setStatusUrenFormulier(urenformulierid, "TRAINEE");
        return urenFormulierService.getUrenFormulierById(urenformulierid);
    }
    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @PutMapping("/admin/{urenformulierid}/setstatus-goedkeuring-admin")
    public UrenFormulier setStatusGoedkeuringAdmin(@PathVariable(value = "urenformulierid") long urenformulierid) {
        urenFormulierService.setStatusUrenFormulier(urenformulierid, "ADMIN");
        return urenFormulierService.getUrenFormulierById(urenformulierid);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BEDRIJF')or #id == principal.userId")
    @PutMapping("/bedrijf/{urenformulierid}/setstatus-goedkeuring-bedrijf")
    public UrenFormulier setStatusGoedkeuringBedrijf(@PathVariable(value = "urenformulierid") long urenformulierid) {
        urenFormulierService.setStatusUrenFormulier(urenformulierid, "BEDRIJF");
        return urenFormulierService.getUrenFormulierById(urenformulierid);
    }

    @PreAuthorize("hasAuthority('APPROVE:URENFORMULIER')")
    public UrenFormulier setAfkeuring(long uid) {
        //Als iemand met de rol Admin of Bedrijf deze methode aanroept,
        // zet deze de statusGoedkeuring terug naar OPEN nadat deze
        // door een bedrijf of admin is afgekeurd.
        getUrenFormulierById(uid).setStatusGoedkeuring(StatusGoedkeuring.OPEN);
        return getUrenFormulierById(uid);
    }
}
