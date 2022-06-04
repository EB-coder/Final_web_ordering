package com.example.demo.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="drinks")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Drink {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String image_url;

    private String name;

    private String price;
}