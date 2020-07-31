package app.qienuren.rest;

import app.qienuren.controller.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminEndpoint {
    @Autowired
    AdminService as;
}
