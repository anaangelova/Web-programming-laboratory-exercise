package mk.finki.ukim.mk.lab.repository;


import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class OrderRepository {

    private final List<Order> orderList;

    public OrderRepository(){
        this.orderList=new ArrayList<>();
    }

    public List<Order> findAllOrders(){
        return orderList;
    }
    public Order addOrder(String balloonColor, String balloonSize, String clientName, String clientAddress){
        Random random=new Random();
        long id= random.nextInt(100);
        Order order=new Order(balloonColor,balloonSize,clientName, clientAddress, id);
        orderList.add(order);
        return order;
    }
}
