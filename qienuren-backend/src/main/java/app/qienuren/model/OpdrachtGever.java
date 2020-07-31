package app.qienuren.model;

import javax.persistence.*;

@Entity
@Table(name = "opdrachtgever")
public class OpdrachtGever {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }
}
