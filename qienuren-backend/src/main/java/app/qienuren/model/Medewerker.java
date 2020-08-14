package app.qienuren.model;

import app.qienuren.controller.MedewerkerRepository;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Medewerker extends Gebruiker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Medewerker() {
    }

    public Medewerker(long id) {
        this.id = id;
    }
    public Medewerker(long id, String naam, String adres, String evtToevoeging, String postcode, String woonplaats, String geboorteDatum, long telefoonNummer, String emailadres) {
    }

    public Medewerker(long id, String naam, String adres, String evtToevoeging, String postcode, String woonplaats, String geboorteDatum, long telefoonNummer, String emailadres, List<UrenFormulier> urenFormulier, List<Bericht> berichtenLijst) {
        super(id, naam, adres, evtToevoeging, postcode, woonplaats, geboorteDatum, telefoonNummer, emailadres, urenFormulier, berichtenLijst);
    }

    public Medewerker(Medewerker medewerker){
        this(medewerker.getId(), medewerker.getNaam(), medewerker.getAdres(), medewerker.getEvtToevoeging(), medewerker.getPostcode(), medewerker.getWoonplaats(), medewerker.getGeboorteDatum(), medewerker.getTelefoonNummer(), medewerker.getEmailadres());
    }

    @Override
    public long getId() {
        return id;
    }


}

