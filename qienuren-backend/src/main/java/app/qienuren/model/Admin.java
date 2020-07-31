package app.qienuren.model;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin extends Gebruiker{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Override
    public long getId() {
        return id;
    }
}
