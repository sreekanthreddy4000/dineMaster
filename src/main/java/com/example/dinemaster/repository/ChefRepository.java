package com.example.dinemaster.repository;

import com.example.dinemaster.model.Chef;
import com.example.dinemaster.model.Restaurant;

import java.util.*;

public interface ChefRepository {

    ArrayList<Chef> getChefs();

    Chef getChefById(int chefId);

    Chef addChef(Chef chef);

    Chef updateChef(int chefId, Chef chef);

    void deleteChef(int chefId);

    Restaurant getChefRestaurant(int chefId);
}