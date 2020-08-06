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
    private double uren;
/*    private double opdrachtUren;
    private double overwerkUren;
    private double verlofUren;
    private boolean ziekteDag;
    private double trainingsUren;*/

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="urenformulierId")
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

    public double getUren() {
        return uren;
    }

    public void setUren(long uren) {
        this.uren = uren;
    }

    public UrenFormulier getUrenformulier() {
        return urenformulier;
    }

    public void setUrenformulier(UrenFormulier urenformulier) {
        this.urenformulier = urenformulier;
    }
}
