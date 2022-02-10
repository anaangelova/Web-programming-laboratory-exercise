package mk.finki.ukim.mk.lab.service.interfaces;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    Order placeOrder(String balloonColor, String balloonSize, User createdUser, LocalDateTime localDateTime);
    List<Order> findAllOrders(Long clientId);
    List<Order> getAllOrders();
    List<Order> searchResultsOrders(String text);

}
