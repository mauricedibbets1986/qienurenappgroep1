package app.qienuren.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GebruikerService {
    @Autowired
    GebruikerRepository gr;
}
