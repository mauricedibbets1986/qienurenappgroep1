package app.qienuren.model;

import javax.persistence.*;

@Entity
@Table(name = "medewerker")
public class Medewerker extends Gebruiker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Override
    public long getId() {
        return id;
    }
}
