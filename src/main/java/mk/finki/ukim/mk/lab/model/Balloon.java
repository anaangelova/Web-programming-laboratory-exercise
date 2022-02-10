package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import mk.finki.ukim.mk.lab.model.enums.BalloonType;

import javax.persistence.*;
import java.util.Random;

@Data
@Entity
public class Balloon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    private Manufacturer manufacturer;

    @Enumerated(EnumType.STRING)
    private BalloonType typeOfBalloon;


    public Balloon(String name, String description, Manufacturer manufacturer) {
        this.name = name;
        this.description = description;
        this.manufacturer=manufacturer;

      this.typeOfBalloon=BalloonType.HEART;

    }
    public Balloon(String name, String description, BalloonType typeOfBalloon) {
        this.name = name;
        this.description = description;
        this.typeOfBalloon=typeOfBalloon;
    }

    public Balloon() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BalloonType getTypeOfBalloon() {
        return typeOfBalloon;
    }

    public void setTypeOfBalloon(BalloonType typeOfBalloon) {
        this.typeOfBalloon = typeOfBalloon;
    }
}
