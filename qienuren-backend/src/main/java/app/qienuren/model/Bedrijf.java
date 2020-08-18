package app.qienuren.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bedrijf")
public class Bedrijf {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String bedrijfsNaam;
    private String contactPersoon;
    private long telefoonNummer;
    private String emailAdres;
    private String adres;
    private String evtToevoeging;
    private String postcode;
    private String plaats;

    @OneToMany
    @JsonManagedReference
    private List<Gebruiker> lijstGebruikers = new ArrayList<>();

    public long getId() { return Id; }
    public void setId(long id) {
        Id = id;
    }

    public String getBedrijfsNaam() {
        return bedrijfsNaam;
    }
    public void setBedrijfsNaam(String bedrijfsNaam) {
        this.bedrijfsNaam = bedrijfsNaam;
    }

    public long getTelefoonNummer() {
        return telefoonNummer;
    }
    public void setTelefoonNummer(long telefoonNummer) {
        this.telefoonNummer = telefoonNummer;
    }

    public String getEmailAdres() {
        return emailAdres;
    }
    public void setEmailAdres(String emailAdres) {
        this.emailAdres = emailAdres;
    }

    public String getAdres() {
        return adres;
    }
    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getEvtToevoeging() {
        return evtToevoeging;
    }
    public void setEvtToevoeging(String evtToevoeging) {
        this.evtToevoeging = evtToevoeging;
    }

    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPlaats() {
        return plaats;
    }
    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public String getContactPersoon() {
        return contactPersoon;
    }
    public void setContactPersoon(String contactPersoon) {
        this.contactPersoon = contactPersoon;
    }


    public List<Gebruiker> getLijstGebruikers() {
        return lijstGebruikers;
    }

    public void setLijstGebruikers(List<Gebruiker> lijstGebruikers) {
        this.lijstGebruikers = lijstGebruikers;
    }

    public void addGebruikerToLijst(Gebruiker gebruiker) {
        lijstGebruikers.add(gebruiker);
        gebruiker.setBedrijf(this);
    }
}
