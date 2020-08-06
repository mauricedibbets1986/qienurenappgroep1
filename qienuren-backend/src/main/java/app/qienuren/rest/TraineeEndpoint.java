package app.qienuren.rest;

import app.qienuren.controller.TraineeRepository;
import app.qienuren.controller.TraineeService;
import app.qienuren.model.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainee")
public class TraineeEndpoint {
    @Autowired
    TraineeService ts;

    @Autowired
    TraineeRepository tr;

    @PostMapping("/new")
    public Trainee addTrainee(@RequestBody Trainee trainee) {
        return ts.addTrainee(trainee);
    }

    @GetMapping("/all")
    public Iterable<Trainee> getTrainees(){
        return ts.getAllTrainees();
    }
}
