package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "confirmationServlet", urlPatterns = "/ConfirmationInfo")
public class ConfirmationInfoServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;
    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService){
        this.springTemplateEngine=springTemplateEngine;
        this.orderService=orderService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(req, resp, req.getServletContext());
        springTemplateEngine.process("confirmationInfo.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //prateni vo baranjeto od korisnikot
        req.getSession().setAttribute("clientName",req.getParameter("clientName"));
        req.getSession().setAttribute("clientAddress",req.getParameter("clientAddress"));
        //od zaglavjeto se zemaat
        req.getSession().setAttribute("clientBrowser",req.getHeader("User-Agent"));
        req.getSession().setAttribute("clientIP",req.getRemoteAddr());

        String balloonColor=(String) req.getSession().getAttribute("balloonColor");
        String balloonSize= (String) req.getSession().getAttribute("balloonSize");
        String clientName=(String) req.getSession().getAttribute("clientName");
        String clientAddress=(String) req.getSession().getAttribute("clientAddress");

        Long clientId=(Long) req.getSession().getAttribute("userId") ;
        orderService.placeOrder(balloonColor,balloonSize,clientName,clientAddress,clientId);
        resp.sendRedirect("/ConfirmationInfo");

    }
}
