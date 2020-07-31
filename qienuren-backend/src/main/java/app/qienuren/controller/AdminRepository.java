package app.qienuren.controller;

import app.qienuren.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface AdminRepository extends CrudRepository<Admin, Long> {
}
