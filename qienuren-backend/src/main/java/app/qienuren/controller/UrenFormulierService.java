package app.qienuren.controller;

import app.qienuren.exceptions.OverwerkException;
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

    public Object addWorkDaytoUrenFormulier(long ufid, long wdid) {
        try {
            ufr.findById(ufid).get().addWerkdayToArray(wdr.findById(wdid).get());
            return ufr.save(ufr.findById(ufid).get());
        } catch (Exception e) {
            return e.getMessage();
        }
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