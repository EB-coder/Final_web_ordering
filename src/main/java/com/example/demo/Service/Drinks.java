package com.example.demo.Service;

import com.example.demo.Entities.Drink;

import java.util.*;

public class Drinks {

    public static List<Drink> getDrinks(){
        ArrayList<Drink> drinks = new ArrayList<>();
        Drink drink = new Drink(null, "https://www.stama.co/wp-content/uploads/CARBONATEDDRINKCOCACOLA05l.jpg",
                "COCA COLA CARBONATED 0.5 L", "55 soms");
        drinks.add(drink);
        drink = new Drink(null, "https://www.stama.co/wp-content/uploads/CARBONATEDDRINKCOCACOLA2l.jpg",
                "COCA COLA CARBONATED 2 L", "135 soms");
        drinks.add(drink);
        drink = new Drink(null, "https://www.stama.co/wp-content/uploads/CARBONATEDDRINKPEPSIMAX330ml.jpg",
                "CARBONATED DRINK PEPSI MAX CAN 330ml", "60 soms");
        drinks.add(drink);
        drink = new Drink(null, "https://www.stama.co/wp-content/uploads/CARBONATEDDRINKSPRITE05l.jpg",
                "CARBONATED DRINK SPRITE 0.5 L", "55 soms");
        drinks.add(drink);
        drink = new Drink(null, "https://www.stama.co/wp-content/uploads/CARBONATEDDRINKSPRITE2l.jpg",
                "CARBONATED DRINK SPRITE 2 L", "130 soms");
        drinks.add(drink);
        drink = new Drink(null, "https://www.stama.co/wp-content/uploads/CARBONATEDDRINKPEPSI500ml.jpg",
                "CARBONATED DRINK PEPSI 0.5 L", "50 soms");
        drinks.add(drink);
        drink = new Drink(null, "https://www.stama.co/wp-content/uploads/CARBONATEDDRINKPEPSI2l.jpg",
                "CARBONATED DRINK PEPSI 2 L", "125 soms");
        drinks.add(drink);
        drink = new Drink(null, "https://www.stama.co/wp-content/uploads/CARBONATEDDRINKPEPSI1l.jpg",
                "CARBONATED DRINK PEPSI 1 L", "80 soms");
        drinks.add(drink);
        drink = new Drink(null, "https://www.stama.co/wp-content/uploads/NESQUIKDRINK180ml.jpg",
                "NESQUIK DRINK 180ml", "60 soms");
        drinks.add(drink);
        drink = new Drink(null, "https://www.stama.co/wp-content/uploads/NESCAFECAPPUCCINOCAN250ml.jpg",
                "NESCAFE CAPPUCCINO CAN 250 ml", "110 soms");
        drinks.add(drink);
        drink = new Drink(null, "https://www.stama.co/wp-content/uploads/CARBONATEDDRINKMIRINDAORANGE500ml1.jpg",
                "CARBONATED DRINK MIRINDA ORANGE 0.5 L", "45 soms");
        drinks.add(drink);
        drink = new Drink(null, "https://www.stama.co/wp-content/uploads/CARBONATEDDRINKMIRINDAORANGECAN330ml.jpg",
                "CARBONATED DRINK MIRINDA ORANGE CAN 330ml", "55 soms");
        drinks.add(drink);
        drink = new Drink(null, "https://www.stama.co/wp-content/uploads/CARBONATEDDRINKSMIRINDA2l.jpg",
                "CARBONATED DRINK MIRINDA ORANGE 2 L", "120 soms");
        drinks.add(drink);
        drink = new Drink(null, "https://www.stama.co/wp-content/uploads/CARBONATEDDRINKSEVENUPCAN330ml2.jpg",
                "CARBONATED DRINK SEVEN UP CAN 330ml", "65 soms");
        drinks.add(drink);
        drink = new Drink(null, "https://www.stama.co/wp-content/uploads/CARBONATEDDRINKSEVENUP500ml1.jpg",
                "CARBONATED DRINK SEVEN UP 0.5 L", "60 soms");
        drinks.add(drink);
        drink = new Drink(null, "https://www.stama.co/wp-content/uploads/ICETEALIPTONLEMONCAN330ml.jpg",
                "ICE TEA LIPTON LEMON CAN 330ml", "70 soms");
        drinks.add(drink);

        return drinks;
    }
}
