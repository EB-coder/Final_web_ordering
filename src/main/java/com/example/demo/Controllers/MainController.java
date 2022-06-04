package com.example.demo.Controllers;

import com.example.demo.Entities.Drink;
import com.example.demo.Entities.User;
import com.example.demo.Service.DBService;
import com.example.demo.Service.Order;
import com.example.demo.Service.RegistrationStatus;
import com.example.demo.Service.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private DBService db;

    @Autowired
    Sender sender;

    private final RegistrationStatus rs = new RegistrationStatus();

    @GetMapping("/main")
    public ModelAndView main(){
        ModelAndView m = new ModelAndView("index");
        List<Drink> drinks = db.getDrinks();
        m.addObject("drinks", drinks);
        return m;
    }

    @GetMapping("/")
    public String slash(){
        return "redirect:main";
    }

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @GetMapping("/reg")
    public ModelAndView regpage(){
        ModelAndView m = new ModelAndView("reg");
        User user = new User();
        m.addObject("user", user);
        m.addObject("status", rs);
        return m;
    }

    @PostMapping("/reg")
    public String reg(@ModelAttribute User user, HttpServletRequest request){
        boolean success = false;
        if(db.checkUser(user.getUsername())){
            rs.setUsed(true);
        }else{
            db.addUser(user);
            rs.setUsed(false);
            try {
                request.login(user.getUsername(), user.getPassword());
                success = true;
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
        if(success){
            return "redirect:main";
        }else {
            return "redirect:reg";
        }
    }

    @GetMapping("/order")
    public ModelAndView order(@RequestParam Integer id){
        ModelAndView m = new ModelAndView("orderpage");
        Order order = new Order();
        Drink drink = db.getDrinkById(id);
        m.addObject("order", order);
        m.addObject("drink", drink);
        return m;
    }

    @PostMapping("/send-order")
    public ModelAndView orderSuccess(Order order, Drink drink){
        ModelAndView m = new ModelAndView("orderpage");
        sender.sendOrder(drink, order);
        order.setSent(true);
        m.addObject("order", order);
        return m;
    }

    @GetMapping("/about")
    public ModelAndView about(){
        return new ModelAndView("about");
    }

    @GetMapping("/error")
    public String err(){
        return "redirect:main";
    }
}
