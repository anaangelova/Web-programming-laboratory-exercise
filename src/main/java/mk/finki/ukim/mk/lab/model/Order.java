package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="allorders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String balloonColor;
    private String balloonSize;

    @ManyToOne
    private User user;

    @ManyToOne
    private ShoppingCart shoppingCart;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateCreated;

    public Order(String balloonColor, String balloonSize, User user, LocalDateTime dateCreated) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.user=user;
        this.dateCreated=dateCreated;

    }
    public Order(String balloonColor){
        this.balloonColor=balloonColor;
    }

    public Order() {

    }
}
