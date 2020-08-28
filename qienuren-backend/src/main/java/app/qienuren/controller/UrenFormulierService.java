package app.qienuren.controller;

import app.qienuren.model.StatusGoedkeuring;
import app.qienuren.model.UrenFormulier;
import app.qienuren.model.Werkdag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UrenFormulierService {

    @Autowired
    UrenFormulierRepository urenFormulierRepository;

    @Autowired
    WerkdagRepository werkdagRepository;

    @Autowired
    WerkdagService werkdagService;

    public Iterable<UrenFormulier> getAllUrenFormulieren() {
        return urenFormulierRepository.findAll();
    }

    public Object addWorkDaytoUrenFormulier(long ufid, long wdid) {
        UrenFormulier uf = urenFormulierRepository.findById(ufid).get();
        Werkdag wd = werkdagRepository.findById(wdid).get();
        try {
            uf.addWerkdayToArray(wd);
            return urenFormulierRepository.save(uf);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public UrenFormulier addNewUrenFormulier(UrenFormulier urenFormulier) {
        urenFormulierRepository.save(urenFormulier);
        int maand = urenFormulier.getMaand().ordinal() + 1;
        YearMonth yearMonth = YearMonth.of(Integer.parseInt(urenFormulier.getJaar()), maand);
        int daysInMonth = yearMonth.lengthOfMonth();
        for (int x = 1; x <= daysInMonth; x++) {
            Werkdag werkdag = werkdagService.addNewWorkday(new Werkdag(x));
            addWorkDaytoUrenFormulier(urenFormulier.getId(), werkdag.getId());
        }
        return urenFormulierRepository.save(urenFormulier);
    }

    public double getTotaalGewerkteUren(long id) {
        return urenFormulierRepository.findById(id).get().getTotaalGewerkteUren();
    }

    public Iterable<UrenFormulier> getUrenFormulierPerMaand(int maandid) {
        List<UrenFormulier> localUren = new ArrayList<>();
        for (UrenFormulier uren : urenFormulierRepository.findAll()) {
            if (uren.getMaand().ordinal() == maandid) {
                localUren.add(uren);
            }
        }
        return localUren;
    }

    public UrenFormulier getUrenFormulierById(long uid) {
        return urenFormulierRepository.findById(uid).get();
    }

    public double getGewerkteUrenByID(long id) {
    return 0.0;
    }

    public UrenFormulier setStatusUrenFormulier(long uid, String welkeGoedkeurder){
        //deze methode zet de statusGoedkeuring van OPEN naar INGEDIEND_GEBRUIKER nadat deze
        // door de gebruiker is ingediend ter goedkeuring
        if (welkeGoedkeurder.equals("GEBRUIKER")) {
            getUrenFormulierById(uid).setStatusGoedkeuring(StatusGoedkeuring.INGEDIEND_GEBRUIKER);
        }
        if(welkeGoedkeurder.equals("ADMIN")) {
            getUrenFormulierById(uid).setStatusGoedkeuring(StatusGoedkeuring.GOEDGEKEURD_ADMIN);
        }
        if(welkeGoedkeurder.equals("BEDRIJF")) {
            getUrenFormulierById(uid).setStatusGoedkeuring(StatusGoedkeuring.GOEDGEKEURD_BEDRIJF);
        }
        return getUrenFormulierById(uid);
    }
}