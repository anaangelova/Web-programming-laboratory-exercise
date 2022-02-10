package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.interfaces.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getOrdersPage(Model model, HttpServletRequest request){
        Long clientId=(Long) request.getSession().getAttribute("currentUserId");
        /*  String clientId=request.getRemoteUser();*/

        model.addAttribute("orders",orderService.findAllOrders(clientId));
        return "userOrders";
    }

    @GetMapping("/allOrders")
    public String getAllOrdersPage(Model model){
        List<Order> allOrders=orderService.getAllOrders();
        model.addAttribute("allOrdersInSystem",allOrders);
        return "listOrders";
    }

    @GetMapping("/searchResults")
    public String getBalloonsByType(@RequestParam String searchQuery, Model model){
        //contact order service to search for data Order by the given parameter

        List<Order> result=orderService.searchResultsOrders(searchQuery);
        model.addAttribute("allOrdersInSystem", result);
        return  "listOrders";


    }

}
