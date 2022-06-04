package com.example.demo.Service;

import com.example.demo.Entities.Drink;
import com.example.demo.Entities.User;
import com.example.demo.Repos.DrinkRepo;
import com.example.demo.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DBService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DrinkRepo dr;

    private Drinks drinks;

    public boolean checkUser(String username){
        Optional<User> user = userRepo.findByUsername(username);
        return user.isPresent();
    }

    public void addUser(User user){
        userRepo.save(user);
    }

    public void addDrinks(){
        if(dr.findAll().size() == 0){
            dr.saveAll(Drinks.getDrinks());
        }
    }

    public Drink getDrinkById(Integer id){
        return dr.findById(id).get();
    }

    public List<Drink> getDrinks(){
        addDrinks();
        return dr.findAll();
    }
}
