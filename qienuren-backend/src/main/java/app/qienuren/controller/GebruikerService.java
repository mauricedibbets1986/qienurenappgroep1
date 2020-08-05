package app.qienuren.controller;

import app.qienuren.model.Gebruiker;
import app.qienuren.model.UrenFormulier;
import app.qienuren.model.Werkdag;
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
}
