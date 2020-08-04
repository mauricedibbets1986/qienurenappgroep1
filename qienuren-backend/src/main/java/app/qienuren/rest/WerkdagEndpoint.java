package app.qienuren.rest;

import app.qienuren.controller.WerkdagService;
import app.qienuren.model.Werkdag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class WerkdagEndpoint {
    @Autowired
    WerkdagService ws;

    @GetMapping("/werkdagen")
    public Iterable<Werkdag> werkdagList() {
        return ws.getAllWorkdays();
    }

    @GetMapping("/werkdag/{id}")
    public Object getWerkdagById(@PathVariable(value="id")long id) {
        return ws.getAllWorkdaysById(id);
    }

    @PostMapping("/werkdag/new")
    public Werkdag addNewWerkdag(@RequestBody Werkdag werkdag) {
        return ws.addNewWorkday(werkdag);
    }
}
