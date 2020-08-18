package app.qienuren.controller;

import app.qienuren.exceptions.OverwerkException;
import app.qienuren.model.Werkdag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
    @Transactional
    public class WerkdagService {
        @Autowired
        WerkdagRepository werkdagRepository;

        public Iterable<Werkdag> getAllWorkdays(){
            System.out.println("Je verzoekt alle werkdagen");
            return werkdagRepository.findAll();
        }

        public Object getAllWorkdaysById(long id) {
            try {
                System.out.println("Je verzoekt naar werkdag Id: "+ id);
                return werkdagRepository.findById(id).get();
            }
            catch(Exception e1){
                System.out.println("Je verzoekt een werkdagId die niet bestaat");
                return "<h1>You Requested User Id That Doesn't Exist</h1>";
            }
        }

        public Werkdag addNewWorkday(Werkdag werkdag) {
            if (werkdag.getUren() > 10) {
                throw new OverwerkException("Je mag niet meer dan 10 uur per dag werken");
            }
            return werkdagRepository.save(werkdag);
        }
    }

//        public Werkdag addNewHours(long hours, long dayId) {
//           wr.findById(dayId).get().setUren(hours);
//           return wr.save(wr.findById(dayId).get());
//        }