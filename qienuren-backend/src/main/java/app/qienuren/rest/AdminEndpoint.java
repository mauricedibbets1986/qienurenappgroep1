package app.qienuren.rest;

import app.qienuren.controller.*;
import app.qienuren.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminEndpoint {
    @Autowired
    AdminService adminService;

    //@Autowired
   //TraineeService traineeService;

    @Autowired
    GebruikerService gebruikerService;

    @Autowired
    UrenFormulierService urenFormulierService;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    GebruikerRepository gebruikerRepository;

    //@Autowired
    //MedewerkerService medewerkerService;

    /*nieuwe gebruikers*/
   // @PostMapping("/new-trainee")
   // public Trainee addTrainee(@RequestBody Trainee trainee) {
       // return traineeService.addTrainee(trainee);
   // }

    @PostMapping("/new-admin")
    public Admin addTrainee(@RequestBody Admin admin) {
        return adminService.addAdmin(admin);
    }


    /*Wijzigen gebruikers*/
    //@PutMapping("users/changedetails/{id}")
    //public void changeDetailsById(@PathVariable(value = "id") long id, @RequestBody Gebruiker gebruiker) {
       // gebruikerService.changeDetails(gebruikerRepository.findById(id).get(), gebruiker);
    //}

    /*Rol gebruiker veranderen*/

    /*Verwijderen gebruikers*/


    /*Overzichten gebruikers*/
   // @GetMapping("/all-trainees")
   // public Iterable<Trainee> getTrainees(){
      //  return traineeService.getAllTrainees();
    //}

    @GetMapping("/all-users")
    public Iterable<Gebruiker> getUsers(){
        return gebruikerService.getAllUsers();
    }

    //@GetMapping("/all-trainees/{rol}")
   //public Gebruiker getGebruikerTrainee(@PathVariable(value="roltrainee") String rolTrainee) ;
    //Gebruiker gebruikerTrainee = GebruikerService.getGebruikerTrainee(rolTrainee);
    //return gebruikerTrainee;



    /* Urenformulieren */
    @GetMapping("/{gebruikerid}/{urenformulierid}/{werkdagid}")
    public Werkdag getWerkdagGebruiker(@PathVariable(value = "gebruikerid") long gid, @PathVariable(value = "urenformulierid") long ufid, @PathVariable(value = "werkdagid") long wdid){
        Werkdag wd = gebruikerService.getWerkdagGebruiker(gid, ufid, wdid);
        return wd;
    }

    @GetMapping("/{gebruikerid}/{urenformulierid}")
    public UrenFormulier getUrenformulierGebruiker(@PathVariable(value = "gebruikerid") long gebruikerid, @PathVariable(value = "urenformulierid") long urenformulierid){
        UrenFormulier urenFormulier = gebruikerService.getUrenformulierGebruiker(gebruikerid, urenformulierid);
        return urenFormulier;
    }
    /*Aanmaken Urenformulier*/
    @PostMapping("urenformulier/new")
    public UrenFormulier addNewUrenFormulier(@RequestBody UrenFormulier urenFormulier) {
        return urenFormulierService.addNewUrenFormulier(urenFormulier);
    }

    }

