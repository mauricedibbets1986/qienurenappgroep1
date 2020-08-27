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
    @PutMapping("/{urenformulierid}/setstatus-indienengebruiker")
    public UrenFormulier setStatusFormulierIngediendGebruiker(@PathVariable(value = "urenformulierid") long urenformulierid) {
        urenFormulierService.setIndienen(urenformulierid, "GEBRUIKER");
        return urenFormulierService.getUrenFormulierById(urenformulierid);
    }
    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @PutMapping("/{urenformulierid}/setstatus-goedkeuring-admin")
    public UrenFormulier setStatusGoedkeuringAdmin(@PathVariable(value = "urenformulierid") long urenformulierid) {
        urenFormulierService.setIndienen(urenformulierid, "ADMIN");
        return urenFormulierService.getUrenFormulierById(urenformulierid);
    }
    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @PutMapping("/{urenformulierid}/setstatus-goedkeuring-bedrijf")
    public UrenFormulier setStatusGoedkeuringBedrijf(@PathVariable(value = "urenformulierid") long urenformulierid) {
        urenFormulierService.setIndienen(urenformulierid, "BEDRIJF");
        return urenFormulierService.getUrenFormulierById(urenformulierid);
    }

//    @PreAuthorize("hasAuthority('APPROVE:URENFORMULIER')")
//    @PutMapping("/{urenformulierid}/{id}/setstatus-goedkeuring")
//    public UrenFormulier setStatusFormulierGoedkeuring(@PathVariable(value = "urenformulierid") long urenformulierid, @PathVariable(value = "id") long id) {
//        StatusGoedkeuring huidigeStatus = urenFormulierService.getUrenFormulierById(urenformulierid).getStatusGoedkeuring();
//        Collection<RoleEntity> roleCollection = gebruikerService.getGebruikerById(id).getRoles();
//        for (RoleEntity rol : roleCollection) {
//            if (rol.equals(Roles.ROLE_ADMIN)) {
//                this.huidigeRol = rol.toString();
//            }
//            if (rol.equals(Roles.ROLE_TRAINEE)) {
//                this.huidigeRol = rol.toString();
//            }
//            if (rol.equals(Roles.ROLE_BEDRIJF)) {
//                this.huidigeRol = rol.toString();
//            }
//            if (rol.equals(Roles.ROLE_MEDEWERKER)) {
//                this.huidigeRol = rol.toString();
//            }
//        }
//        urenFormulierService.setGoedkeuring(huidigeStatus, huidigeRol, urenformulierid);
//        return urenFormulierService.getUrenFormulierById(urenformulierid);
//    }

    @PreAuthorize("hasAuthority('APPROVE:URENFORMULIER')")
    public UrenFormulier setAfkeuring(long uid) {
        //Als iemand met de rol Admin of Bedrijf deze methode aanroept,
        // zet deze de statusGoedkeuring terug naar OPEN nadat deze
        // door een bedrijf of admin is afgekeurd.
        getUrenFormulierById(uid).setStatusGoedkeuring(StatusGoedkeuring.OPEN);
        return getUrenFormulierById(uid);
    }
}
