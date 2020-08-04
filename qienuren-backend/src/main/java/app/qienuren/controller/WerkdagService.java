package app.qienuren.controller;

import app.qienuren.model.Werkdag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
    @Transactional
    public class WerkdagService {
        @Autowired
        WerkdagRepository wr;

        public Iterable<Werkdag> getAllWorkdays(){
            System.out.println("Je verzoekt alle werkdagen");
            return wr.findAll();
        }

        public Object getAllWorkdaysById(long id) {
            try {
                System.out.println("Je verzoekt naar werkdag Id: "+ id);
                return wr.findById(id).get();
            }
            catch(Exception e1){
                System.out.println("Je verzoekt een werkdagId die niet bestaat");
                return "<h1>You Requested User Id That Doesn't Exist</h1>";
            }
        }

        public Werkdag addNewWorkday(Werkdag werkdag) {
            return wr.save(werkdag);
        }
    }

//        public Werkdag addNewHours(long hours, long dayId) {
//           wr.findById(dayId).get().setUren(hours);
//           return wr.save(wr.findById(dayId).get());
//        }