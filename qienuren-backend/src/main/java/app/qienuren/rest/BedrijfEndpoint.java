package app.qienuren.rest;

import app.qienuren.controller.BedrijfRepository;
import app.qienuren.controller.BedrijfService;
import app.qienuren.model.Bedrijf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bedrijf")
public class BedrijfEndpoint {
    @Autowired
    BedrijfService bedrijfService;

    @Autowired
    BedrijfRepository bedrijfRepository;

//    //Onderstaande methode alleen door Admin!
//    @PostMapping("/new")
//    public Bedrijf addBedrijf(@RequestBody Bedrijf bedrijf) {
//        return bedrijfService.addBedrijfbyID(bedrijf);
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public Iterable<Bedrijf> getBedrijven(){
        return bedrijfService.getAllBedrijven();
    }

    //Onderstaande methode alleen door Admin!
    @PreAuthorize("hasAuthority('DELETE:GEBRUIKER')")
    @DeleteMapping("/delete/{id}")
    public String deleteBedrijfById(@PathVariable(value = "id") long id){
        bedrijfService.deleteBedrijfById(id);
        return "Bedrijf met id " + id + " is verwijderd";
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @PutMapping("/changedetails/{id}")
    public void changeDetailsById(@PathVariable(value = "id") long id, @RequestBody Bedrijf bedrijf) {
        bedrijfService.changeDetails(bedrijfRepository.findById(id).get(), bedrijf);
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @PutMapping("/{bedrijfid}/{gebruikerid}")
    public void addGebruikerToBedrijf(@PathVariable(value = "bedrijfid") long bedrijfId, @PathVariable(value = "gebruikerid") long gebruikerId) {
        bedrijfService.addGebruikerToBedrijf(bedrijfId, gebruikerId);
    }

    @PreAuthorize("hasAnyRole('ADMIN')or #id == principal.userId")
    @GetMapping("/naam/{naam}")
    public Iterable<Bedrijf> getBedrijfByNaam(@PathVariable(value = "naam") String bedrijfsNaam){
        return bedrijfService.getByBedrijfsNaam(bedrijfsNaam);
    }
}
