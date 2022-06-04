package com.example.demo.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationStatus {

    private boolean isUsed;

    public RegistrationStatus(){
        isUsed = false;
    }
}
