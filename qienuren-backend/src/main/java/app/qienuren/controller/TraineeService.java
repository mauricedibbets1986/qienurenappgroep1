package app.qienuren.controller;

import app.qienuren.model.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TraineeService {
    @Autowired TraineeRepository tr;

    public Trainee addTrainee(Trainee trainee){
        System.out.println("In employee Service");
        return tr.save(trainee);
    }

    public Iterable<Trainee> getAllTrainees(){
        return tr.findAll();
    }
}
