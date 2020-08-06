package app.qienuren.controller;

import app.qienuren.model.Medewerker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MedewerkerService {
    @Autowired
    MedewerkerRepository mr;

    public Medewerker addMedewerker(Medewerker medewerker) {
        return mr.save(medewerker);
    }

    public Iterable<Medewerker> getAllMedewerkers() {
        return mr.findAll();
    }
}
