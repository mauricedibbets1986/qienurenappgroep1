package app.qienuren.controller;

import app.qienuren.model.Gebruiker;
import app.qienuren.model.Medewerker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MedewerkerService {
    @Autowired
    MedewerkerRepository medewerkerrepository;

    @Autowired
    GebruikerRepository gebruikerRepository;

    public Medewerker addMedewerker(Medewerker medewerker) {
        return medewerkerrepository.save(medewerker);
    }

    public Iterable<Medewerker> getAllMedewerkers() {
        return medewerkerrepository.findAll();
    }

    public Medewerker naarMedewerkerVeranderen(long id) {
        Gebruiker temp = gebruikerRepository.findById(id).get();
        Medewerker medewerker = new Medewerker();
        medewerker.setNaam(temp.getNaam());
        medewerker.setAdres(temp.getAdres());
        medewerker.setEvtToevoeging(temp.getEvtToevoeging());
        medewerker.setPostcode(temp.getPostcode());
        medewerker.setWoonplaats(temp.getWoonplaats());
        medewerker.setGeboorteDatum(temp.getGeboorteDatum());
        medewerker.setTelefoonNummer(temp.getTelefoonNummer());
        medewerker.setEmailadres(temp.getEmailadres());
/*        medewerker.setUrenFormulier(temp.getUrenFormulier());
        medewerker.setBerichtenLijst(temp.getBerichtenLijst());*/
        return medewerkerrepository.save(medewerker);
    }
}
