package app.qienuren.rest;

import app.qienuren.controller.MedewerkerService;
import app.qienuren.model.Medewerker;
import app.qienuren.model.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/medewerker")
public class MedewerkerEndpoint {

    @Autowired
    MedewerkerService ms;

    @PostMapping("/new")
    public Medewerker addMedewerker(@RequestBody Medewerker medewerker) {
        return ms.addMedewerker(medewerker);
    }

    @GetMapping("/all")
    public Iterable<Medewerker> getMedewerkers(){
        return ms.getAllMedewerkers();
    }
}
