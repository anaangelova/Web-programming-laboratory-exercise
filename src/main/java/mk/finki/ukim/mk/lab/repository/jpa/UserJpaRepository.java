package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<User, Long> {


    boolean existsByName(String name);

    User findByName(String name);
    List<User> findByNameLike(String name);
}
