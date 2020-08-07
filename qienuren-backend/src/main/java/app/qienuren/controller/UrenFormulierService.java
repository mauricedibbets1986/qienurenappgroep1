package app.qienuren.controller;

import app.qienuren.model.Gebruiker;
import app.qienuren.model.UrenFormulier;
import app.qienuren.model.Werkdag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UrenFormulierService {

    @Autowired
    UrenFormulierRepository ufr;

    @Autowired
    WerkdagRepository wdr;


    public Iterable<UrenFormulier> getAllUrenFormulieren() {
        return ufr.findAll();
    }

    public void addWorkDaytoUrenFormulier(long ufid, long wdid) {
        UrenFormulier uf = ufr.findById(ufid).get();
        Werkdag wd = wdr.findById(wdid).get();
        uf.addWerkdayToArray(wd);
        ufr.save(uf);
    }

    public UrenFormulier addNewUrenFormulier(UrenFormulier urenFormulier) {
        return ufr.save(urenFormulier);
    }

    public double getTotaalGewerkteUren(long id) {
        return ufr.findById(id).get().getTotaalGewerkteUren();
    }

    public Iterable<UrenFormulier> getUrenFormulierPerMaand(int maandid) {
        List<UrenFormulier> localUren = new ArrayList<>();
        for (UrenFormulier uren : ufr.findAll()) {
            if (uren.getMaand().ordinal() == maandid) {
                localUren.add(uren);
            }
        }
        return localUren;
    }
}