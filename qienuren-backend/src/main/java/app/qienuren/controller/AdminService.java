package app.qienuren.controller;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminService {

    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
        }
    }

