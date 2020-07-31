package app.qienuren.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "urenFormulier")
public class UrenFormulier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String datum;
    private double opdrachtUren;
    private double overwerkUren;
    private double verlofUren;
    private double ziekteUren;
    private double trainingsUren;
    private String opmerking;
    private boolean goedkeuring;
    private double vakantieUren;
    private double reiskosten;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="GebruikerId")
    private Gebruiker gebruiker;

    public long getId() {
        return id;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public double getOpdrachtUren() {
        return opdrachtUren;
    }

    public void setOpdrachtUren(double opdrachtUren) {
        this.opdrachtUren = opdrachtUren;
    }

    public double getOverwerkUren() {
        return overwerkUren;
    }

    public void setOverwerkUren(double overwerkUren) {
        this.overwerkUren = overwerkUren;
    }

    public double getVerlofUren() {
        return verlofUren;
    }

    public void setVerlofUren(double verlofUren) {
        this.verlofUren = verlofUren;
    }

    public double getZiekteUren() {
        return ziekteUren;
    }

    public void setZiekteUren(double ziekteUren) {
        this.ziekteUren = ziekteUren;
    }

    public double getTrainingsUren() {
        return trainingsUren;
    }

    public void setTrainingsUren(double trainingsUren) {
        this.trainingsUren = trainingsUren;
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

    public double getVakantieUren() {
        return vakantieUren;
    }

    public void setVakantieUren(double vakantieUren) {
        this.vakantieUren = vakantieUren;
    }

    public double getReiskosten() {
        return reiskosten;
    }

    public void setReiskosten(double reiskosten) {
        this.reiskosten = reiskosten;
    }
}
