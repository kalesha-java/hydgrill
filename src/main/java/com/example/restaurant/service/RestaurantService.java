package com.example.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.repository.RestaurantRepository;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public ResponseEntity<Restaurant> createRestaurant(Restaurant restaurant) {
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return new ResponseEntity<>(savedRestaurant, HttpStatus.CREATED);
    }

    public ResponseEntity<Restaurant> updateRestaurant(Long id, Restaurant restaurant) {
        Optional<Restaurant> existingRestaurantOptional = restaurantRepository.findById(id);
        if (existingRestaurantOptional.isPresent()) {
            Restaurant existingRestaurant = existingRestaurantOptional.get();
            existingRestaurant.setLogo(restaurant.getLogo());
            existingRestaurant.setName(restaurant.getName());
            existingRestaurant.setEmail(restaurant.getEmail());
            existingRestaurant.setPhoneNumber(restaurant.getPhoneNumber());
            existingRestaurant.setTimings(restaurant.getTimings());
            existingRestaurant.setAddress(restaurant.getAddress());
            Restaurant updatedRestaurant = restaurantRepository.save(existingRestaurant);
            return new ResponseEntity<>(updatedRestaurant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Restaurant> getRestaurantById(Long id) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        return restaurantOptional.map(restaurant -> new ResponseEntity<>(restaurant, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteRestaurant(Long id) {
        if (restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Restaurant> getRestaurantByPhoneNumber(String phoneNumber) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByPhoneNumber(phoneNumber);
        return restaurantOptional.map(restaurant -> new ResponseEntity<>(restaurant, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Restaurant> getRestaurantByEmail(String email) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByEmail(email);
        return restaurantOptional.map(restaurant -> new ResponseEntity<>(restaurant, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
