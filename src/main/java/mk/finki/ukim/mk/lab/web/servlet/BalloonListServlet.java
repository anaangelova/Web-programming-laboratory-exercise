package mk.finki.ukim.mk.lab.web.servlet;

import mk.finki.ukim.mk.lab.service.interfaces.BalloonService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*@WebServlet(name = "balloonServlet", urlPatterns = "")*/
public class BalloonListServlet extends HttpServlet {
    private final BalloonService balloonService;
    private final SpringTemplateEngine springTemplateEngine;
    public BalloonListServlet(BalloonService balloonService, SpringTemplateEngine springTemplateEngine){
        this.balloonService=balloonService;
        this.springTemplateEngine=springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(req, resp, req.getServletContext());
        context.setVariable("allBalloons",balloonService.listAll());
        springTemplateEngine.process("listBalloons.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //dodaden metod otposle, od koga trebashe da se dodade Filter
        req.getSession().setAttribute("balloonColor", req.getParameter("color")); //za da ovoj atribut bide dostapen vo ramkite na ovoj servlet koj se smeta za javen.
        resp.sendRedirect("/selectBalloon");
    }
}
