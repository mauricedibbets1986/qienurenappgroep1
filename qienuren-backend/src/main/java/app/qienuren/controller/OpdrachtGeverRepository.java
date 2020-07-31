package app.qienuren.controller;

import app.qienuren.model.OpdrachtGever;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface OpdrachtGeverRepository extends CrudRepository<OpdrachtGever, Long> {
}
