package app.qienuren.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "werkdag")
public class Werkdag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String datumDag;
    private double opdrachtUren;
    private double overwerkUren;
    private double verlofUren;
    private boolean ziekteDag;
    private double trainingsUren;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "urenformulier_id")
    private UrenFormulier urenformulier;

    public long getId() {
        return id;
    }

    public String getDatumDag() {
        return datumDag;
    }

    public void setDatumDag(String datumDag) {
        this.datumDag = datumDag;
    }

    public UrenFormulier getUrenformulier() {
        return urenformulier;
    }

    public void setUrenformulier(UrenFormulier urenformulier) {
        this.urenformulier = urenformulier;
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

    public boolean isZiekteDag() {
        return ziekteDag;
    }

    public void setZiekteDag(boolean ziekteDag) {
        this.ziekteDag = ziekteDag;
    }

    public double getTrainingsUren() {
        return trainingsUren;
    }

    public void setTrainingsUren(double trainingsUren) {
        this.trainingsUren = trainingsUren;
    }
}
