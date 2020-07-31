package app.qienuren.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gebruiker")
public class Gebruiker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String naam;
    private String adres;
    private String geboorteDatum;
    private long telefoonNummer;



    @OneToMany(mappedBy = "gebruiker")
    @JsonManagedReference
    private List<UrenFormulier> urenFormulier = new ArrayList<>();

    @OneToMany(mappedBy = "gebruiker")
    @JsonManagedReference
    private List<Bericht> berichtenLijst = new ArrayList<>();

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getGeboorteDatum() {
        return geboorteDatum;
    }

    public void setGeboorteDatum(String geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    public long getTelefoonNummer() {
        return telefoonNummer;
    }

    public void setTelefoonNummer(long telefoonNummer) {
        this.telefoonNummer = telefoonNummer;
    }

    public List<UrenFormulier> getUrenFormulier() {
        return urenFormulier;
    }

    public void setUrenFormulier(List<UrenFormulier> urenFormulier) {
        this.urenFormulier = urenFormulier;
    }

    public List<Bericht> getBerichtenLijst() {
        return berichtenLijst;
    }

    public void setBerichtenLijst(List<Bericht> berichtenLijst) {
        this.berichtenLijst = berichtenLijst;
    }
}
