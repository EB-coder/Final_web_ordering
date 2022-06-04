package com.example.demo.Service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {

    private String email;

    private boolean isSent;

    private int quantity;

    public Order(){
        isSent = false;
        quantity = 1;
    }
}
