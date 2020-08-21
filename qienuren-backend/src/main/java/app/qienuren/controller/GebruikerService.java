package app.qienuren.controller;

import app.qienuren.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Year;

@Service
@Transactional
public class GebruikerService {
    @Autowired
    GebruikerRepository gebruikerRepository;

    @Autowired
    UrenFormulierRepository urenformulierrepository;

    @Autowired
    WerkdagRepository werkdagrepository;



    //public Trainee addTrainee(Trainee trainee){
    //return traineerepository.save(trainee);
    //}

    public void addUrenFormulierToGebruiker(long gid, long ufid) {
        Gebruiker g = gebruikerRepository.findById(gid).get();
        UrenFormulier uf = urenformulierrepository.findById(ufid).get();
        g.addUrenFormulierToArray(uf);
        gebruikerRepository.save(g);
    }

    public Werkdag getWerkdagGebruiker(long gebruikerid, long urenformulierid, long werkdagid) {
       /* Gebruiker g = gr.findById(gebruikerid).get();
        UrenFormulier uf = ufr.findById(urenformulierid).get();*/
        Werkdag wd = werkdagrepository.findById(werkdagid).get();
        return wd;
    }

    public Gebruiker getGebruikerById(long id) {

        return gebruikerRepository.findById(id).get();
    }

    public Iterable<Gebruiker> getByRole(String role){
        return this.gebruikerRepository.findByRole(role);
    }

    public Iterable<Gebruiker> getByVoornaam(String voornaam){
        return this.gebruikerRepository.findByVoornaam(voornaam);
    }

    public Iterable<Gebruiker> getByAchternaam(String achternaam){
        return this.gebruikerRepository.findByAchternaam(achternaam);
    }

    public Iterable<Gebruiker> getByWoonplaats(String woonplaats){
        return this.gebruikerRepository.findByWoonplaats(woonplaats);
    }

    public Iterable<Gebruiker> getByGeboorteDatum(LocalDate geboorteDatum){
        return this.gebruikerRepository.findByGeboorteDatum(geboorteDatum);
    }


    public Iterable<Gebruiker> getByEmail(String emailadres){
        return this.gebruikerRepository.findByEmail(emailadres);
    }

    public void deleteGebruikerById(long id) {
        gebruikerRepository.deleteById(id);
    }

    public Iterable<Gebruiker> getAllUsers() {
        return gebruikerRepository.findAll();
    }

    public UrenFormulier getUrenformulierGebruiker(long gebruikerid, long urenformulierid) {
        UrenFormulier urenformulier = urenformulierrepository.findById(urenformulierid).get();
        return urenformulier;
    }

    public Gebruiker addGebruiker(Gebruiker gebruiker) {
        return gebruikerRepository.save(gebruiker);

    }

    public Iterable<UrenFormulier> getUrenformulierenVanGebruiker(long id) {
        return gebruikerRepository.findById(id).get().getUrenFormulier();
    }

    public UrenFormulier changestatusUrenFormulier(UrenFormulier urenFormulier){
        urenFormulier.setStatusGoedkeuring(StatusGoedkeuring.CHECKGEBRUIKER);
        return urenFormulier;
    }


}
