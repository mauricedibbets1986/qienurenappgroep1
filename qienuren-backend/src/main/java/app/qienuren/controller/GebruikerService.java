package app.qienuren.controller;

import app.qienuren.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GebruikerService {
    @Autowired
    GebruikerRepository gebruikerrepository;

    @Autowired
    UrenFormulierRepository urenformulierrepository;

    @Autowired
    WerkdagRepository werkdagrepository;

    public void addUrenFormulierToGebruiker(long gid, long ufid) {
        Gebruiker g = gebruikerrepository.findById(gid).get();
        UrenFormulier uf = urenformulierrepository.findById(ufid).get();
        g.addUrenFormulierToArray(uf);
        gebruikerrepository.save(g);
    }

    public Werkdag getWerkdagGebruiker(long gebruikerid, long urenformulierid, long werkdagid) {
       /* Gebruiker g = gr.findById(gebruikerid).get();
        UrenFormulier uf = ufr.findById(urenformulierid).get();*/
        Werkdag wd = werkdagrepository.findById(werkdagid).get();
        return wd;
    }

    public Gebruiker getGebruikerById(long id) {
        return gebruikerrepository.findById(id).get();
    }

    public Gebruiker changeDetails(Gebruiker gebruiker, Gebruiker gebruikerUpdate) {
        if (gebruikerUpdate.getNaam() != null) {
            gebruiker.setNaam(gebruikerUpdate.getNaam());
        }
        if (gebruikerUpdate.getAdres() != null) {
            gebruiker.setAdres(gebruikerUpdate.getAdres());
        }
        if (gebruikerUpdate.getEvtToevoeging() != null){
            gebruiker.setEvtToevoeging(gebruikerUpdate.getEvtToevoeging());
        }
        if (gebruikerUpdate.getPostcode() != null) {
            gebruiker.setPostcode(gebruikerUpdate.getPostcode());
        }
        if (gebruikerUpdate.getWoonplaats() != null) {
            gebruiker.setWoonplaats(gebruikerUpdate.getWoonplaats());
        }
        if (gebruikerUpdate.getGeboorteDatum() != null) {
            gebruiker.setGeboorteDatum(gebruikerUpdate.getGeboorteDatum());
        }
        if (gebruikerUpdate.getTelefoonNummer() != 0) {
            gebruiker.setTelefoonNummer(gebruikerUpdate.getTelefoonNummer());
        }
        if(gebruikerUpdate.getEmailadres() != null){
            gebruiker.setEmailadres(gebruikerUpdate.getEmailadres());
        }



        return gebruikerrepository.save(gebruiker);
    }

    public void deleteGebruikerById(long id) {
        gebruikerrepository.deleteById(id);
    }

    public Iterable<Gebruiker> getAllUsers() {
        return gebruikerrepository.findAll();
    }

    public UrenFormulier getUrenformulierGebruiker(long gebruikerid, long urenformulierid) {
        UrenFormulier urenformulier = urenformulierrepository.findById(urenformulierid).get();
        return urenformulier;
    }
}
