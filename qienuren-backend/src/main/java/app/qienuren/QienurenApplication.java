package app.qienuren;

import app.qienuren.controller.GebruikerService;
import app.qienuren.model.Role;
import app.qienuren.model.UrenFormulier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QienurenApplication {


    public static void main(String[] args) {
        SpringApplication.run(QienurenApplication.class, args);
        //regel hieronder niet aanpassen aub
        System.out.println("Uren App groep 1. It's Alive!");

    }

}
 