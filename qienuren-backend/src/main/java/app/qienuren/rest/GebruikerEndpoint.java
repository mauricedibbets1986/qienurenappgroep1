package app.qienuren.rest;

import app.qienuren.controller.BedrijfService;
import app.qienuren.controller.GebruikerRepository;
import app.qienuren.controller.GebruikerService;
import app.qienuren.controller.UrenFormulierService;
import app.qienuren.gebruikerDto.*;
import app.qienuren.model.Gebruiker;
import app.qienuren.model.StatusGoedkeuring;
import app.qienuren.model.UrenFormulier;
import app.qienuren.model.Werkdag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@RestController
@RequestMapping("/api")
public class GebruikerEndpoint {
    @Autowired
    GebruikerService gebruikerService;

    @Autowired
    UrenFormulierService urenFormulierService;

    @Autowired
    GebruikerRepository gebruikerRepository;

    @Autowired
    BedrijfService bedrijfService;

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @GetMapping("/gebruiker/{gebruikerid}/{urenformulierid}/{werkdagid}")
    public Werkdag getWerkdagGebruiker(@PathVariable(value = "gebruikerid") long gid, @PathVariable(value = "urenformulierid") long ufid, @PathVariable(value = "werkdagid") long wdid) {
        Werkdag wd = gebruikerService.getWerkdagGebruiker(gid, ufid, wdid);
        return wd;
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @GetMapping("/gebruiker/{id}")
    public Gebruiker getGebruikerById(@PathVariable(value = "id") long id) {
        System.out.println("endpoint called");
        return gebruikerService.getGebruikerById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @GetMapping("/gebruiker/rol-id/{id}")
    public Object getGebruikerrolById(@PathVariable(value = "id") long id) {
        return gebruikerService.getGebruikerById(id).getClass();
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @GetMapping("/gebruiker/rol/{role}")
    public Iterable<Gebruiker> getGebruikerByRole(@PathVariable(value = "role") Roles role) {
        return gebruikerService.getByRole(role);
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @GetMapping("/gebruiker/voornaam/{voornaam}")
    public Iterable<Gebruiker> getGebruikerByVoornaam(@PathVariable(value = "voornaam") String voornaam) {
        return gebruikerService.getByVoornaam(voornaam);
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @GetMapping("/gebruiker/achternaam/{achternaam}")
    public Iterable<Gebruiker> getGebruikerByAchternaam(@PathVariable(value = "achternaam") String achternaam) {
        return gebruikerService.getByAchternaam(achternaam);
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @GetMapping("/gebruiker/woonplaats/{woonplaats}")
    public Iterable<Gebruiker> getGebruikerByWoonplaats(@PathVariable(value = "woonplaats") String woonplaats) {
        return gebruikerService.getByWoonplaats(woonplaats);
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @GetMapping("/gebruiker/geboorteDatum/{geboorteDatum}")
    public Iterable<Gebruiker> getGebruikerByGeboorteDatum(@PathVariable(value = "geboorteDatum") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate geboorteDatum) {
        return gebruikerService.getByGeboorteDatum(geboorteDatum);
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @GetMapping("/gebruiker/email/{emailadres}")
    public Gebruiker getEmail(@PathVariable(value = "emailadres") String emailadres) {
        return gebruikerService.getByEmail(emailadres);
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @GetMapping("gebruiker/all") //moet naar "/users"
    public Iterable<Gebruiker> getAll() {
        return gebruikerService.getAllUsers();
    }

    //@PutMapping("/changedetails/{id}")
    //public void changeDetailsById(@PathVariable(value = "id") long id, @RequestBody Gebruiker gebruiker) {
    // gebruikerService.changeDetails(gebruikerRepository.findById(id).get(), gebruiker);
    // }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @PutMapping("/gebruiker/{gebruikerid}/{urenformulierid}")
    public void updateUrenFormulierToGebruiker(@PathVariable(value = "urenformulierid") long ufid, @PathVariable(value = "gebruikerid") long gid) {
        gebruikerService.addUrenFormulierToGebruiker(gid, ufid);
    }

    @PreAuthorize("hasAuthority('DELETE:GEBRUIKER')")
    @DeleteMapping("/gebruiker/delete/{id}")
    public String deleteGebruikerById(@PathVariable(value = "id") long id) {
        gebruikerService.deleteGebruikerById(id);
        return "Gebruiker met id " + id + " verwijderd";
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @GetMapping("/gebruiker/{id}/urenformulieren")
    public Iterable<UrenFormulier> getUrenformulierenByGebruiker(@PathVariable(value = "id") long id) {
        return gebruikerService.getUrenformulierenVanGebruiker(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @GetMapping("/gebruiker/gewerkteuren/{id}")
    public double getTotaalGewerkteUren(@PathVariable(value = "id") long id) {
        return urenFormulierService.getGewerkteUrenByID(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @PutMapping("/gebruiker/{id}/setstatus-checkgebruiker")
    public UrenFormulier setStatusFormulierCheckGebruiker(@PathVariable(value = "id") long id) {
        urenFormulierService.getUrenFormulierById(id).setStatusGoedkeuring(StatusGoedkeuring.INGEDIEND_GEBRUIKER);
        return urenFormulierService.getUrenFormulierById(id);
    }

    @PreAuthorize("hasAuthority('CREATE:GEBRUIKER')")
    @PostMapping("/admin/users/addAdmin")
    public GebruikerDetailsResponse createAdmin(@RequestBody GebruikerDetailsRequest gebruikerDetails) {
        GebruikerDto gebruikerDto = new GebruikerDto();
        BeanUtils.copyProperties(gebruikerDetails, gebruikerDto);
        //asList will return an arraylist and it needs to be unique set of values, because user cannot have multiple roles with same name, so using HashSet
        // also in UserPrincipal i made the List a Collection and new arraylist into new Hashset==> Collection<GrantedAuthority> authorities = new HashSet<>();
        //set role Admin in Dto
        gebruikerDto.setRoles(new HashSet<>(Arrays.asList(Roles.ROLE_ADMIN.name())));

        GebruikerDetailsResponse returnValue = new GebruikerDetailsResponse();
        GebruikerDto createdUser = gebruikerService.createUser(gebruikerDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        System.out.println("Admin toevoegen");
        return returnValue;
    }

    @PreAuthorize("hasAuthority('CREATE:GEBRUIKER')")
    @PostMapping("/admin/users/addTrainee")
    public GebruikerDetailsResponse createTrainee(@RequestBody GebruikerDetailsRequest gebruikerDetails) {
        GebruikerDto userDto = new GebruikerDto();
        BeanUtils.copyProperties(gebruikerDetails, userDto);

        userDto.setRoles(new HashSet<>(Arrays.asList(Roles.ROLE_TRAINEE.name())));

        GebruikerDetailsResponse returnValue = new GebruikerDetailsResponse();
        GebruikerDto createdUser = gebruikerService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        System.out.println("Trainee toevoegen");
        return returnValue;
    }

    @PreAuthorize("hasAuthority('CREATE:GEBRUIKER')")
    @PostMapping("/admin/users/addMedewerker")
    public GebruikerDetailsResponse createMedewerker(@RequestBody GebruikerDetailsRequest gebruikerDetails) {
        GebruikerDto gebruikerDto = new GebruikerDto();
        BeanUtils.copyProperties(gebruikerDetails, gebruikerDto);

        gebruikerDto.setRoles(new HashSet<>(Arrays.asList(Roles.ROLE_MEDEWERKER.name())));

        GebruikerDetailsResponse returnValue = new GebruikerDetailsResponse();
        GebruikerDto createdUser = gebruikerService.createUser(gebruikerDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        System.out.println("Medewerker toevoegen");
        return returnValue;
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @GetMapping("/users/bedrijf/{id}")
    public BedrijfDetailsResponse getBedrijfByUserId(@PathVariable(value = "id") String id) {
        BedrijfDetailsResponse returnValue = new BedrijfDetailsResponse();

        GebruikerDto gebruikerDto = bedrijfService.getBedrijfByUserId(id);
        BeanUtils.copyProperties(gebruikerDto, returnValue);

        return returnValue;
    }

    @PreAuthorize("hasAuthority('CREATE:GEBRUIKER')")
    @PostMapping("/admin/users/addBedrijf")
    public BedrijfDetailsResponse createBedrijf(@RequestBody BedrijfDetailsRequest bedrijfDetails) {
        GebruikerDto gebruikerDto = new GebruikerDto();
        BeanUtils.copyProperties(bedrijfDetails, gebruikerDto);
        //asList will return an arraylist and it needs to be unique set of values, because user cannot have multiple roles with same name, so using HashSet
        // also in UserPrincipal i made the List a Collection and new arraylist into new Hashset==> Collection<GrantedAuthority> authorities = new HashSet<>();
        //set role Admin in Dto
        gebruikerDto.setRoles(new HashSet<>(Arrays.asList(Roles.ROLE_BEDRIJF.name())));

        BedrijfDetailsResponse returnValue = new BedrijfDetailsResponse();
        GebruikerDto createdUser = bedrijfService.createBedrijf(gebruikerDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        System.out.println("Bedrijf toevoegen");
        return returnValue;
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @GetMapping("/users/{id}")
    public GebruikerDetailsResponse getUserByUserId(@PathVariable(value = "id") String id) {
        GebruikerDetailsResponse returnValue = new GebruikerDetailsResponse();

        GebruikerDto userDto = gebruikerService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, returnValue);

        return returnValue;
    }

    @PreAuthorize("hasAnyRole('ADMIN') or #id == principal.userId")
    @PutMapping("/users/{id}")
    public GebruikerDetailsResponse updateGebruiker(@PathVariable String id, @RequestBody GebruikerDetailsRequest gebruikerDetailsRequest) {
        GebruikerDetailsResponse returnValue = new GebruikerDetailsResponse();

        GebruikerDto gebruikerDto = new GebruikerDto();
        BeanUtils.copyProperties(gebruikerDetailsRequest, gebruikerDto);

        GebruikerDto updatedGebruiker = gebruikerService.updateGebruiker(id, gebruikerDto);
        BeanUtils.copyProperties(updatedGebruiker, returnValue);

        return returnValue;
    }

    @PreAuthorize("hasAuthority('DELETE:GEBRUIKER')")
    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable String userId){
        gebruikerService.deleteGebruiker(userId);
        return "Delete Succes";
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/users")
    public Iterable<Gebruiker> getAllUsers() {
        return gebruikerService.getAllUsers();
    }
}


