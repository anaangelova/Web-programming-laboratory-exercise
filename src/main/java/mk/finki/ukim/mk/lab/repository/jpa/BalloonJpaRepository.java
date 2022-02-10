package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.enums.BalloonType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BalloonJpaRepository extends JpaRepository<Balloon, Long> {



    List<Balloon> findAllByName(String text);

    List<Balloon> getByTypeOfBalloon(BalloonType typeOfBalloon);


}
