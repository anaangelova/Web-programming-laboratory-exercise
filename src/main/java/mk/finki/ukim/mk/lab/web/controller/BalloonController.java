package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/balloons")
public class BalloonController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model, HttpServletRequest request) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Balloon> balloonList=balloonService.listAll();
        if(request.getSession().getAttribute("userId")==null) {
            Long idSession = (long) (Math.random() * 1000);
            request.getSession().setAttribute("userId", idSession);
        }
        model.addAttribute("balloons", balloonList);
        return "listBalloons";
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long manufacturer){

        this.balloonService.save(name,description,manufacturer);
        return "redirect:/balloons";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.balloonService.deleteById(id);
        return "redirect:/balloons";
    }
    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model){
        if(this.balloonService.findById(id).isPresent()){
            Balloon balloon=this.balloonService.findById(id).get();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("balloon", balloon);
            return "add-balloon";
        }

        return "redirect:/balloons?error=BalloonNotFound";

    }
    @GetMapping("/add-form")
    public String addProductPage(Model model){
        List<Manufacturer> manufacturers=manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "add-balloon";
    }


    @PostMapping("/submit") //moj dopolnitelen metod
    public String getBalloonSizePage(HttpServletRequest req){
        req.getSession().setAttribute("balloonColor", req.getParameter("color"));
        return "redirect:/selectBalloon";
    }
}
