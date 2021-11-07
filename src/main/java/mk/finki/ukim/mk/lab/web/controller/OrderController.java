package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getOrdersPage(Model model, HttpServletRequest request){
        Long clientId=(Long) request.getSession().getAttribute("userId");
        model.addAttribute("orders",orderService.findAllOrders(clientId));
        return "userOrders";
    }
}
