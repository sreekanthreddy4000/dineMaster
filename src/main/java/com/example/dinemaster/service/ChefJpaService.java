package com.example.dinemaster.service;

import com.example.dinemaster.model.Restaurant;
import com.example.dinemaster.model.Chef;
import com.example.dinemaster.repository.ChefRepository;
import com.example.dinemaster.repository.RestaurantJpaRepository;
import com.example.dinemaster.repository.ChefJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChefJpaService implements ChefRepository {

    @Autowired
    private ChefJpaRepository chefJpaRepository;

    @Autowired
    private RestaurantJpaRepository restaurantJpaRepository;

    @Override
    public ArrayList<Chef> getChefs() {
        List<Chef> list = chefJpaRepository.findAll();
        ArrayList<Chef> chefs = new ArrayList<>(list);
        return chefs;
    }

    @Override
    public Chef getChefById(int chefId) {
        try {
            Chef chef = chefJpaRepository.findById(chefId).get();
            return chef;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Chef addChef(Chef chef) {
        Restaurant restaurant = chef.getRestaurant();
        int restaurantId = restaurant.getId();
        try {
            restaurant = restaurantJpaRepository.findById(restaurantId).get();
            chef.setRestaurant(restaurant);
            chefJpaRepository.save(chef);
            return chef;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Chef updateChef(int chefId, Chef chef) {
        try {
            Chef newChef = chefJpaRepository.findById(chefId).get();

            if (chef.getRestaurant() != null) {
                int restaurantId = chef.getRestaurant().getId();
                Restaurant newRestaurant = restaurantJpaRepository.findById(restaurantId).get();
                newChef.setRestaurant(newRestaurant);
            }

            if (chef.getFirstName() != null) {
                newChef.setFirstName(chef.getFirstName());
            }

            if (chef.getLastName() != null) {
                newChef.setLastName(chef.getLastName());
            }

            if (chef.getExpertise() != null) {
                newChef.setExpertise(chef.getExpertise());
            }

            if (chef.getExperienceYears() != 0) {
                newChef.setExperienceYears(chef.getExperienceYears());
            }

            chefJpaRepository.save(newChef);
            return newChef;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteChef(int chefId) {
        try {
            chefJpaRepository.deleteById(chefId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public Restaurant getChefRestaurant(int chefId) {
        try {

            Chef chef = chefJpaRepository.findById(chefId).get();
            Restaurant restaurant = chef.getRestaurant();
            return restaurant;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

}
