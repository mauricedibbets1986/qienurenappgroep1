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
    BedrijfService bedrijfService;

    @Autowired
    BedrijfRepository bedrijfRepository;

    //Onderstaande methode alleen door Admin!
    @PostMapping("/new")
    public Bedrijf addBedrijf(@RequestBody Bedrijf bedrijf) {
        return bedrijfService.addBedrijfbyID(bedrijf);
    }

    @GetMapping("/all")
    public Iterable<Bedrijf> getBedrijven(){
        return bedrijfService.getAllBedrijven();
    }

    //Onderstaande methode alleen door Admin!
    @DeleteMapping("/delete/{id}")
    public String deleteBedrijfById(@PathVariable(value = "id") long id){
        bedrijfService.deleteBedrijfById(id);
        return "Bedrijf met id " + id + " is verwijderd";
    }

    @PutMapping("/changedetails/{id}")
    public void changeDetailsById(@PathVariable(value = "id") long id, @RequestBody Bedrijf bedrijf) {
        bedrijfService.changeDetails(bedrijfRepository.findById(id).get(), bedrijf);
    }

    @PutMapping("/{bedrijfid}/{gebruikerid}")
    public void addGebruikerToBedrijf(@PathVariable(value = "bedrijfid") long bedrijfId, @PathVariable(value = "gebruikerid") long gebruikerId) {
        bedrijfService.addGebruikerToBedrijf(bedrijfId, gebruikerId);
    }

    @GetMapping("/naam/{naam}")
    public Iterable<Bedrijf> getBedrijfByNaam(@PathVariable(value = "naam") String bedrijfsNaam){
        return bedrijfService.getByBedrijfsNaam(bedrijfsNaam);
    }



}
