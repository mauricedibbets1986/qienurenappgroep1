package app.qienuren.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Trainee extends Gebruiker{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long bedrijfId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "bedrijf_id")
    private Bedrijf bedrijf;

    @Override
    public long getId() {
        return id;
    }

    public long getBedrijfId() {
        return bedrijfId;
    }
    public void setBedrijfId(long bedrijfId) {
        this.bedrijfId = bedrijfId;
    }


}
