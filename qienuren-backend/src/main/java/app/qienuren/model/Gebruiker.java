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
    //private String voornaam;
    //private String achternaam;
    private String adres;
    private String evtToevoeging;
    private String postcode;
    private String woonplaats;
    private String geboorteDatum;
    private long telefoonNummer;
    private String emailadres;
    private String role;

    @OneToMany
    @JsonManagedReference
    private List<UrenFormulier> urenFormulier = new ArrayList<>();

    @OneToMany
    @JsonManagedReference
    private List<Bericht> berichtenLijst = new ArrayList<>();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "bedrijf_id")
    private Bedrijf bedrijf;

    public Gebruiker() {
    }


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

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String getEmailadres() {
        return emailadres;
    }

    public void setEmailadres(String emailadres) {

        this.emailadres = emailadres;
    }

    public String getEvtToevoeging() {
        return evtToevoeging;
    }

    public void setEvtToevoeging(String evtToevoeging) {
        this.evtToevoeging = evtToevoeging;
    }

    public String getRol() {
        return role;
    }

    public void setRol(String role) {
        this.role = role;
    }

    public void addUrenFormulierToArray(UrenFormulier uf) {
        urenFormulier.add(uf);
        uf.setGebruiker(this);
    }

    public Bedrijf getBedrijf() {
        return bedrijf;
    }

    public void setBedrijf(Bedrijf bedrijf) {
        this.bedrijf = bedrijf;
    }
}
