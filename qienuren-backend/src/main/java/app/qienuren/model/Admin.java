package app.qienuren.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Admin extends Gebruiker{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Override
    public long getId() {
        return id;
    }
}
