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
  
    @PreAuthorize("hasAnyRole('ADMIN','TRAINEE','MEDEWERKER')or #id == principal.userId")
    @PutMapping("/gebruiker/{urenformulierid}/setstatus-indienengebruiker")
    //Als iemand met de rol Gebruiker deze methode aanroept,
    // zet deze de statusGoedkeuring van OPEN naar INGEDIEND_GEBRUIKER
    public UrenFormulier setStatusFormulierIngediendGebruiker(@PathVariable(value = "urenformulierid") long urenformulierid) {
        urenFormulierService.setStatusUrenFormulier(urenformulierid, "GEBRUIKER");
        return urenFormulierService.getUrenFormulierById(urenformulierid);
    }
    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @PutMapping("/admin/{urenformulierid}/setstatus-goedkeuring-admin")
    //Als iemand met de rol Admin deze methode aanroept,
    // zet deze de statusGoedkeuring van GOEDGEKEURD_BEDRIJF of INGEDIEND_MEDEWERKER naar
    //GOEDGEKEURD_ADMIN
    public UrenFormulier setStatusGoedkeuringAdmin(@PathVariable(value = "urenformulierid") long urenformulierid) {
        urenFormulierService.setStatusUrenFormulier(urenformulierid, "ADMIN");
        return urenFormulierService.getUrenFormulierById(urenformulierid);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BEDRIJF')or #id == principal.userId")
    @PutMapping("/bedrijf/{urenformulierid}/setstatus-goedkeuring-bedrijf")
    //Als iemand met de rol Bedrijf deze methode aanroept,
    //zet deze de statusGoedkeuring van INGEDIEND_TRAINEE naar
    //GOEDGEKEURD_BEDRIJF
    public UrenFormulier setStatusGoedkeuringBedrijf(@PathVariable(value = "urenformulierid") long urenformulierid) {
        urenFormulierService.setStatusUrenFormulier(urenformulierid, "BEDRIJF");
        return urenFormulierService.getUrenFormulierById(urenformulierid);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BEDRIJF')or #id == principal.userId")
    @PutMapping("/{urenformulierid}/setstatus-afkeuring")
    public UrenFormulier setAfkeuring(@PathVariable(value = "urenformulierid") long urenformulierid) {
        //Als iemand met de rol Admin of Bedrijf deze methode aanroept,
        // zet deze de statusGoedkeuring terug naar OPEN nadat deze
        // door een bedrijf of admin is afgekeurd.
        getUrenFormulierById(urenformulierid).setStatusGoedkeuring(StatusGoedkeuring.OPEN);
        return getUrenFormulierById(urenformulierid);
    }
}
