package mk.finki.ukim.mk.lab.service.interfaces;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.User;

import java.util.Optional;

public interface UserService {
    User save(String name);
}
