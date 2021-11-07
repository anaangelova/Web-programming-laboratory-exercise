package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class Balloon {
    private String name;
    private String description;
    private Long id;
    private Manufacturer manufacturer;
    public Balloon(String name, String description, Manufacturer manufacturer) {
        this.name = name;
        this.description = description;
        this.manufacturer=manufacturer;
        this.id=(long) (Math.random() *1000);
    }
    public Balloon(String name, String description) {
        this.name = name;
        this.description = description;
        this.id=(long) (Math.random() *1000);
    }
}
