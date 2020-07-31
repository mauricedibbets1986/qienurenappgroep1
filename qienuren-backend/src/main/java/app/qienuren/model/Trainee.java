package app.qienuren.model;

import javax.persistence.*;

@Entity
@Table(name = "trainee")
public class Trainee extends Gebruiker{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Override
    public long getId() {
        return id;
    }
}
