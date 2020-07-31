package app.qienuren.rest;

import app.qienuren.controller.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TraineeEndpoint {
    @Autowired
    TraineeService ts;
}
