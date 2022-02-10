package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.interfaces.OrderService;
import mk.finki.ukim.mk.lab.service.interfaces.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/ConfirmationInfo")
public class ConfirmationInfoController {
    private final OrderService orderService;
    private final UserService userService;

    public ConfirmationInfoController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public String getConfirmationPage(){
        return "confirmationInfo";
    }

    @PostMapping
    public String saveConfirmationInfo(@RequestParam String clientName, @RequestParam("localDateTime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime, HttpServletRequest req ){

        //prateni vo baranjeto od korisnikot
        req.getSession().setAttribute("clientName",clientName);
        //od zaglavjeto se zemaat
        req.getSession().setAttribute("clientBrowser",req.getHeader("User-Agent"));
        req.getSession().setAttribute("clientIP",req.getRemoteAddr());
        req.getSession().setAttribute("OrderTime",localDateTime);
        String balloonColor=(String) req.getSession().getAttribute("balloonColor");
        String balloonSize= (String) req.getSession().getAttribute("balloonSize");

        //tuka da go zapishe korisnikot vo bazata - treba pristap do user service
        User createdUser=userService.save(clientName);
        req.getSession().setAttribute("currentUserId",createdUser.getId());

        /*Long clientId=(Long) req.getSession().getAttribute("userId") ;*/

        orderService.placeOrder(balloonColor,balloonSize,createdUser, localDateTime);
        return "redirect:/ConfirmationInfo";

    }
}
