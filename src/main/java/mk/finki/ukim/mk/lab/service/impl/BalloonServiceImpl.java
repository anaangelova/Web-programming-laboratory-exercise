package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.enums.BalloonType;
import mk.finki.ukim.mk.lab.model.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.mk.lab.repository.jpa.BalloonJpaRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ManufacturerJpaRepository;
import mk.finki.ukim.mk.lab.service.interfaces.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final BalloonJpaRepository balloonRepository;

    private final ManufacturerJpaRepository manufacturerRepository;

    public BalloonServiceImpl(BalloonJpaRepository balloonRepository, ManufacturerJpaRepository manufacturerRepository){
        this.balloonRepository=balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }
    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAll();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return balloonRepository.findAllByName(text);
    }

    @Override
    public Optional<Balloon> save(String name, String description, Long manufacturer) {

        Manufacturer manufacturerToFind=manufacturerRepository.findById(manufacturer)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturer));
        return Optional.of(balloonRepository.save(new Balloon(name,description,manufacturerToFind)));

   /*     return balloonRepository.save(name, description, manufacturerToFind);*/

    }

    @Override
    public void deleteById(Long id) {
        balloonRepository.deleteById(id);
    }

    @Override
    public Optional<Balloon> findById(Long id) {
        return balloonRepository.findById(id);
    }

    @Override
    public List<Balloon> getByType(String type) {
        BalloonType type1= BalloonType.valueOf(type);
        return balloonRepository.getByTypeOfBalloon(type1);
    }
}
