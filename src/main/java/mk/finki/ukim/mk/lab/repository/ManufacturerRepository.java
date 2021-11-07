package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ManufacturerRepository {

    private final List<Manufacturer> manufacturers;

    public ManufacturerRepository() {

        this.manufacturers = new ArrayList<>();
        manufacturers.add(new Manufacturer("Manf1", "USA","Addr1"));
        manufacturers.add(new Manufacturer("Manf2", "USA","Addr2"));
        manufacturers.add(new Manufacturer("Manf3", "USA","Addr3"));
        manufacturers.add(new Manufacturer("Manf4", "USA","Addr4"));
        manufacturers.add(new Manufacturer("Manf5", "USA","Addr5"));


    }
    public List<Manufacturer> findAll(){
        return manufacturers;
    }
    public Optional<Manufacturer> findById(Long id) {
        return manufacturers.stream().filter(i -> i.getId().equals(id)).findFirst();
    }
}
