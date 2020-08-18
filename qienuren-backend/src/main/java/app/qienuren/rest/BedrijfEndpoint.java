package app.qienuren.rest;

import app.qienuren.controller.BedrijfRepository;
import app.qienuren.controller.BedrijfService;
import app.qienuren.model.Bedrijf;
import app.qienuren.model.Gebruiker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bedrijf")
public class BedrijfEndpoint {

    @Autowired
    BedrijfService bs;

    @Autowired
    BedrijfRepository br;

    //Onderstaande methode alleen door Admin!
    @PostMapping("/new")
    public Bedrijf addBedrijf(@RequestBody Bedrijf bedrijf) {
        return bs.addBedrijfbyID(bedrijf);
    }

    @GetMapping("/all")
    public Iterable<Bedrijf> getBedrijven(){
        return bs.getAllBedrijven();
    }

    //Onderstaande methode alleen door Admin!
    @DeleteMapping("/delete/{id}")
    public String deleteBedrijfById(@PathVariable(value = "id") long id){
        bs.deleteBedrijfById(id);
        return "Bedrijf met id " + id + " is verwijderd";
    }

    @PutMapping("/changedetails/{id}")
    public void changeDetailsById(@PathVariable(value = "id") long id, @RequestBody Bedrijf bedrijf) {
        bs.changeDetails(br.findById(id).get(), bedrijf);
    }

    @PutMapping("/{bedrijfid}/{gebruikerid}")
    public void addGebruikerToBedrijf(@PathVariable(value = "bedrijfid") long bedrijfId, @PathVariable(value = "gebruikerid") long gebruikerId) {
        bs.addGebruikerToBedrijf(bedrijfId, gebruikerId);
    }

    @GetMapping("/naam/{naam}")
    public Iterable<Bedrijf> getBedrijfByNaam(@PathVariable(value = "naam") String bedrijfsNaam){
        return bs.getByBedrijfsNaam(bedrijfsNaam);
    }



}
