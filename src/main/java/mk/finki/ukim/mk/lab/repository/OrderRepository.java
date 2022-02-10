package mk.finki.ukim.mk.lab.repository;


import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {

    private final List<Order> orderList;

    public OrderRepository(){
        this.orderList=new ArrayList<>();
    }

    public List<Order> findAllOrders(Long clientId){

        /*return orderList.stream().filter(o -> o.getClientId().equals(clientId)).collect(Collectors.toList());*/
        return new ArrayList<Order>();
    }
    public Order addOrder(String balloonColor, String balloonSize, String clientName, String clientAddress, Long clientId){
        /*Order order=new Order(balloonColor,balloonSize, clientId);
        orderList.add(order);
        return order;*/
        return new Order();
    }
}
