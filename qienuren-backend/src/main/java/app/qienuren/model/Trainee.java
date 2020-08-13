package app.qienuren.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Trainee extends Gebruiker{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "bedrijf_id")
    private Bedrijf bedrijf;

    @Override
    public long getId() {
        return id;
    }
}
