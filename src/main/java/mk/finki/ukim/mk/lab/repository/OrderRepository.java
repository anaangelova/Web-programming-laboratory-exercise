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

        return orderList.stream().filter(o -> o.getClientId().equals(clientId)).collect(Collectors.toList());
    }
    public Order addOrder(String balloonColor, String balloonSize, String clientName, String clientAddress, Long clientId){
        Random random=new Random();
        long id= random.nextInt(100);
        Order order=new Order(balloonColor,balloonSize,clientName, clientAddress, id, clientId);
        orderList.add(order);
        return order;
    }
}
