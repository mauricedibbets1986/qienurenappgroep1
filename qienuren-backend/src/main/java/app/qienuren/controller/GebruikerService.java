package app.qienuren.controller;

import app.qienuren.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GebruikerService {
    @Autowired
    GebruikerRepository gr;

    @Autowired
    UrenFormulierRepository ufr;


    @Autowired
    WerkdagRepository wr;

    public void addUrenFormulierToGebruiker(long gid, long ufid) {
        Gebruiker g = gr.findById(gid).get();
        UrenFormulier uf = ufr.findById(ufid).get();
        g.addUrenFormulierToArray(uf);
        gr.save(g);
    }

    public Werkdag getWerkdagGebruiker(long gid, long ufid, long wdid) {
       /* Gebruiker g = gr.findById(gid).get();
        UrenFormulier uf = ufr.findById(ufid).get();*/
        Werkdag wd = wr.findById(wdid).get();
        return wd;
    }

    public Gebruiker getGebruikerById(long id) {
        return gr.findById(id).get();
    }

    public Gebruiker changeDetails(Gebruiker gebruiker, Gebruiker gebruikerUpdate) {
        if (gebruikerUpdate.getNaam() != null) {
            gebruiker.setNaam(gebruikerUpdate.getNaam());
        }
        if (gebruikerUpdate.getAdres() != null) {
            gebruiker.setAdres(gebruikerUpdate.getAdres());
        }
        if (gebruikerUpdate.getTelefoonNummer() != 0) {
            gebruiker.setTelefoonNummer(gebruikerUpdate.getTelefoonNummer());
        }
        if (gebruikerUpdate.getGeboorteDatum() != null) {
                gebruiker.setGeboorteDatum(gebruikerUpdate.getGeboorteDatum());
        }
        return gr.save(gebruiker);
    }

    public void deleteGebruikerById(long id) {
        gr.deleteById(id);
    }
}
