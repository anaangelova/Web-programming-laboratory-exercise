package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.UserJpaRepository;
import mk.finki.ukim.mk.lab.service.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserJpaRepository userRepository;

    public UserServiceImpl(UserJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(String name) {
        //ako vekje postoe da ne se dodade povtorno treba
        if(userRepository.existsByName(name))
            return userRepository.findByName(name);
        return userRepository.save(new User(name));
    }
}
