package app.qienuren.gebruikerDto;


import app.qienuren.model.Gebruiker;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class BedrijfDetailsResponse {
    private String userId;
    private String email;
    private String bedrijfsNaam;
    private String contactPersoon;

    @OneToMany
    @JsonManagedReference
    private List<Gebruiker> lijstGebruikers = new ArrayList<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBedrijfsNaam() {
        return bedrijfsNaam;
    }

    public void setBedrijfsNaam(String bedrijfsNaam) {
        this.bedrijfsNaam = bedrijfsNaam;
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
}