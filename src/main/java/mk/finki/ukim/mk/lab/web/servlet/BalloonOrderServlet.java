package mk.finki.ukim.mk.lab.web.servlet;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "balloonOrder", urlPatterns = "/BalloonOrder")
public class BalloonOrderServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine){
        this.springTemplateEngine=springTemplateEngine;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("balloonSize",req.getParameter("size"));
        WebContext context=new WebContext(req, resp, req.getServletContext());
        springTemplateEngine.process("deliveryInfo.html",context,resp.getWriter());
    }
}
