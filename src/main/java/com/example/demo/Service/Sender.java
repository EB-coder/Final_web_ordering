package com.example.demo.Service;

import com.example.demo.Entities.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    @Autowired
    JavaMailSender mailSender;

    public void sendOrder(Drink drink, Order order){
        SimpleMailMessage message = new SimpleMailMessage();
        String subject = "Delivery from DrinksShop.kg";
        message.setFrom("drinks.shop@gmail.com");
        message.setTo(order.getEmail());
        message.setSubject(subject);
        message.setText("Hi!\nHere's your drink: \uD83E\uDDC3\uD83C\uDF77" +
                "\uD83E\uDD43 \uD83C\uDF78 \uD83C\uDF79 \uD83E\uDDC9 \n" + drink.getName() + "" +
                "   ENJOY! \uD83E\uDD29\uD83E\uDD29");
        mailSender.send(message);
    }
}