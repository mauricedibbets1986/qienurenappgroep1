package app.qienuren.controller;

import web.dto.UserRegistrationDto;
import app.qienuren.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);
}
