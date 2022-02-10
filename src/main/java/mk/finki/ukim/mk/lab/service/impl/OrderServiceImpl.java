package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.OrderJpaRepository;
import mk.finki.ukim.mk.lab.repository.jpa.UserJpaRepository;
import mk.finki.ukim.mk.lab.service.interfaces.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderJpaRepository orderRepository;
    private final UserJpaRepository userRepository;
    public OrderServiceImpl(OrderJpaRepository orderRepository, UserJpaRepository userRepository){
        this.orderRepository=orderRepository;
        this.userRepository=userRepository;
    }
    @Override
    public Order placeOrder(String balloonColor, String balloonSize, User createdUser, LocalDateTime localDateTime) {

        return orderRepository.save(new Order(balloonColor,balloonSize,createdUser, localDateTime));


    }

    @Override
    public List<Order> findAllOrders(Long clientId) {

        User currentUser=userRepository.getById(clientId);
        return orderRepository.findAllByUser(currentUser);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> searchResultsOrders(String text) {
        List<Order> result=new ArrayList<>();
        result.addAll(orderRepository.findAllByBalloonColorOrBalloonSizeLike(text,text));
        result.addAll(orderRepository.findAllByUserName(text));
       /* if(isNumeric(text)){
            Long id=Long.parseLong(text);
            result.addAll(orderRepository.findAllByOrderId(id));
            result.addAll(orderRepository.findAllByUserId(id));
        }
*/
        return  result.stream().distinct().collect(Collectors.toList());
    }


    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Long d = Long.parseLong(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
