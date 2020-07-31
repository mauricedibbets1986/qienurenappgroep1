package app.qienuren.rest;

import app.qienuren.controller.UrenFormulierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrenFormulierEndpoint {
    @Autowired
    UrenFormulierService ufr;
}
