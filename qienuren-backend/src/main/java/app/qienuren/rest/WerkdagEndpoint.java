package app.qienuren.rest;

import app.qienuren.controller.WerkdagService;
import app.qienuren.model.Werkdag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/werkdag")
public class WerkdagEndpoint {
    @Autowired
    WerkdagService werkdagService;

    @GetMapping("/werkdagen")
    public Iterable<Werkdag> werkdagList() {
        return werkdagService.getAllWorkdays();
    }

    @GetMapping("/{id}")
    public Object getWerkdagById(@PathVariable(value="id")long id) {
        return werkdagService.getAllWorkdaysById(id);
    }

    @PostMapping("/new")
    public Object addNewWerkdag(@RequestBody Werkdag werkdag) {
        try {
            return werkdagService.addNewWorkday(werkdag);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PutMapping("/edit/{id}")
    public Werkdag editWerkdag(@PathVariable(value = "id") Long id, @RequestBody Werkdag werkdag) {
       return werkdagService.editWerkdag(id, werkdag);
    }
}
