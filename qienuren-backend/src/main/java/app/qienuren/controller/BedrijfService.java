package app.qienuren.controller;

import app.qienuren.model.Bedrijf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BedrijfService {

    @Autowired
    BedrijfRepository br;

    public Bedrijf addBedrijfbyID(Bedrijf bedrijf){
       return br.save(bedrijf);
    }

    public Iterable<Bedrijf> getAllBedrijven(){
        return br.findAll();
    }

    public void deleteBedrijfById(long id) {
        br.deleteById(id);
    }

    public Bedrijf changeDetails(Bedrijf bedrijf, Bedrijf bedrijfUpdate) {
        if (bedrijfUpdate.getBedrijfsNaam() != null) {
            bedrijf.setBedrijfsNaam(bedrijfUpdate.getBedrijfsNaam());
        }
        if (bedrijfUpdate.getAdres() != null) {
            bedrijf.setAdres(bedrijfUpdate.getAdres());
        }
        if (bedrijfUpdate.getEvtToevoeging() != null) {
            bedrijf.setEvtToevoeging(bedrijfUpdate.getEvtToevoeging());
        }
        if (bedrijfUpdate.getPostcode() != null) {
            bedrijf.setPostcode(bedrijfUpdate.getPostcode());
        }
        if (bedrijfUpdate.getPlaats() != null){
            bedrijf.setPlaats(bedrijfUpdate.getPlaats());
        }
        if (bedrijfUpdate.getTelefoonNummer() != 0) {
            bedrijf.setTelefoonNummer(bedrijfUpdate.getTelefoonNummer());
        }
        if (bedrijfUpdate.getContactPersoon() != null) {
            bedrijf.setContactPersoon(bedrijfUpdate.getContactPersoon());
        }
        if (bedrijfUpdate.getEmailAdres() != null) {
            bedrijf.setEmailAdres(bedrijfUpdate.getEmailAdres());
        }
        return br.save(bedrijf);
        }
    }

