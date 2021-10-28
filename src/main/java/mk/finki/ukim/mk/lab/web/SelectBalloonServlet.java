package mk.finki.ukim.mk.lab.web;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="selectBalloonServlet", urlPatterns = "/selectBalloon")
public class SelectBalloonServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    public SelectBalloonServlet(SpringTemplateEngine springTemplateEngine){
        this.springTemplateEngine=springTemplateEngine;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           /* req.getSession().setAttribute("balloonColor", req.getParameter("color")); ova ne treba tuka za da mozhe da se implementira filter za bezbednost*/
            WebContext context = new WebContext(req, resp, req.getServletContext());
            springTemplateEngine.process("selectBalloonSize.html", context, resp.getWriter());

    }


}
