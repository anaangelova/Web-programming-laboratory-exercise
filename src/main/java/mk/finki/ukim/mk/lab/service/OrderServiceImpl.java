package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.repository.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }
    @Override
    public Order placeOrder(String balloonColor,String balloonSize, String clientName, String clientAddress, Long clientId) {
        return orderRepository.addOrder(balloonColor,balloonSize, clientName, clientAddress, clientId);

    }

    @Override
    public List<Order> findAllOrders(Long clientId) {
        return orderRepository.findAllOrders(clientId);
    }
}
