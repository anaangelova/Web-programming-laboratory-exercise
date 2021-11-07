package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class Order {
    private String balloonColor;
    private String balloonSize;
    private String clientName;
    private String clientAddress;
    private Long orderId;
    private Long clientId;

    public Order(String balloonColor, String balloonSize, String clientName, String clientAddress, Long orderId, Long clientId) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.orderId = orderId;
        this.clientId=clientId;
    }
    public Order(String balloonColor, String clientName, String clientAddress){
        this.balloonColor=balloonColor;
        this.clientAddress=clientAddress;
        this.clientName=clientName;
    }
}
