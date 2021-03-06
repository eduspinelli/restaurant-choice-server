package com.restaurant.choice.service;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.choice.domain.model.Restaurant;
import com.restaurant.choice.repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {

  private final RestaurantRepository restaurantRepository;

  @Autowired
  public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
    this.restaurantRepository = restaurantRepository;
  }

  @Override
  public Restaurant getMostVotedRestaurant() {

    List<Restaurant> mostVotedRestaurants = restaurantRepository.getMaxVote();

    return mostVotedRestaurants.get(new Random().nextInt(mostVotedRestaurants.size()));
  }

  @Override
  public List<Restaurant> resetRestaurantVotes() {

    List<Restaurant> restaurants = restaurantRepository.findByVotesGreaterThanZero();

    restaurants.forEach(Restaurant::clearVotes);

    restaurantRepository.saveAll(restaurants);

    return restaurants;
  }

}
