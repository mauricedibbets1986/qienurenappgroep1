package app.qienuren.model;

import app.qienuren.exceptions.OverwerkException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "urenFormulier")
public class UrenFormulier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double totaalGewerkteUren;
    @Enumerated(EnumType.STRING)
    private Maand maand;
    private String jaar;
    private String opmerking;
    private boolean goedkeuring;

//  private long ziekDagen;
//  private long vakantieUren;
//  private double reiskosten;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "Gebruiker_Id")
    private Gebruiker gebruiker;

    @OneToMany
    @JoinColumn(name="werkdag_id")
    private List<Werkdag> werkdag = new ArrayList<>();

    public long getId() {
        return id;
    }

    public String getOpmerking() {
        return opmerking;
    }

    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

    public boolean isGoedkeuring() {
        return goedkeuring;
    }

    public void setGoedkeuring(boolean goedkeuring) {
        this.goedkeuring = goedkeuring;
    }

    public String getJaar() {
        return jaar;
    }

    public void setJaar(String jaar) {
        this.jaar = jaar;
    }

    public Gebruiker getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }

    public List<Werkdag> getWerkdag() {
        return werkdag;
    }

    public void setWerkdag(List<Werkdag> werkdag) {
        this.werkdag = werkdag;
    }

    public double getTotaalGewerkteUren() {
        return totaalGewerkteUren;
    }

    public void setTotaalGewerkteUren(double totaalGewerkteUren) {
        this.totaalGewerkteUren = totaalGewerkteUren;
    }

    public Maand getMaand() {
        return maand;
    }

    public void setMaand(Maand maand) {
        this.maand = maand;
    }

    public void addWerkdayToArray(Werkdag wd) throws Exception {
        werkdag.add(wd);
        calculateTotaalGewerkt(wd);
    }

    public void calculateTotaalGewerkt(Werkdag wd) throws Exception {
        totaalGewerkteUren += wd.getUren();
        checkOverUren();

    }

    private void checkOverUren() throws Exception {
        if (totaalGewerkteUren >= 50) {
            throw new OverwerkException("HO STOP JE HEBT TEVEEL GEWERKT DEZE WEEK");
        }
    }
}
