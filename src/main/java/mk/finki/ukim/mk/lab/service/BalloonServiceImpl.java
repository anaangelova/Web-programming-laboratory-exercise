package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.mk.lab.repository.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.ManufacturerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService{

    private final BalloonRepository balloonRepository;
    private final ManufacturerRepository manufacturerRepository;
    public BalloonServiceImpl(BalloonRepository balloonRepository, ManufacturerRepository manufacturerRepository){
        this.balloonRepository=balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }
    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAllBalloons();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return balloonRepository.findAllByNameOrDescription(text);
    }

    @Override
    public Optional<Balloon> save(String name, String description, Long manufacturer) {

        Manufacturer manufacturerToFind=manufacturerRepository.findById(manufacturer)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturer));
        return balloonRepository.save(name, description, manufacturerToFind);

    }

    @Override
    public void deleteById(Long id) {
        balloonRepository.deleteById(id);
    }

    @Override
    public Optional<Balloon> findById(Long id) {
        return balloonRepository.findById(id);
    }
}
