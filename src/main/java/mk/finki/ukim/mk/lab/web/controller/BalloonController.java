package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.enums.BalloonType;
import mk.finki.ukim.mk.lab.service.interfaces.BalloonService;
import mk.finki.ukim.mk.lab.service.interfaces.ManufacturerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public String getBalloonsPage(@RequestParam(required = false) String error,
                                  @RequestParam(required = false) String filter,
                                  Model model, HttpServletRequest request) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Balloon> balloonList=new ArrayList<>();
        if(filter!=null && !filter.isEmpty()){
            balloonList=balloonService.getByType(filter);
            model.addAttribute("balloonsBySelectedType",balloonList);
        }else balloonList=balloonService.listAll();

        if(request.getSession().getAttribute("userId")==null) {
            Long idSession = (long) (Math.random() * 1000);
            request.getSession().setAttribute("userId", idSession);
        }

        model.addAttribute("balloons", balloonList);
        List<String> typeBalloons= Arrays.stream(BalloonType.values()).map(t -> t.name()).collect(Collectors.toList());

        model.addAttribute("balloonTypes", typeBalloons);
        return "listBalloons";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveBalloon(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long manufacturer){

        this.balloonService.save(name,description,manufacturer);
        return "redirect:/balloons";
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteProduct(@PathVariable Long id) {
        this.balloonService.deleteById(id);
        return "redirect:/balloons";
    }
    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProductPage(Model model){
        List<Manufacturer> manufacturers=manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "add-balloon";
    }


    @PostMapping("/submit") //moj dopolnitelen metod
    public String getBalloonSizePage(HttpServletRequest req){
        req.getSession().setAttribute("balloonColor", req.getParameter("color"));
        return "selectBalloonSize";
    }

    @PostMapping("/sizeBalloon") //moj dopolnitelen metod
    public String sizeBalloonsMethod(HttpServletRequest req){
        req.getSession().setAttribute("balloonSize",req.getParameter("size"));
        return "deliveryInfo";
    }
    @PostMapping("/filterBalloons")
    public String getBalloonsByType(HttpServletRequest req){
       String selectedType=req.getParameter("balloonType");
        return "redirect:/balloons?filter="+selectedType;
    }
}
