import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
package com.example.demo1;
public class searchRestaurant {

    public static void main(String[] args) {
        // List of restaurants
        List<String> restaurants = List.of(
                "Pizza Hut",
                "Burger King",
                "Domino's",
                "KFC",
                "Subway",
                "McDonald's",
                "Taco Bell"
        );

        // Create a Scanner for user input
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a word or letters to search for restaurants:");
        String input = scanner.nextLine().trim().toLowerCase();

        // Validate input
        if (input.isEmpty()) {
            System.out.println("Please enter a valid search term.");
        } else {
            // Find matching restaurants using streams
            List<String> matchingRestaurants = restaurants.stream()
                    .filter(restaurant -> restaurant.toLowerCase().contains(input))//filter method processes the restaurant list and finds matches based on the input substring.
                    .collect(Collectors.toList()); //The collect method gathers the results into a new list.


            // Display results
            if (matchingRestaurants.isEmpty()) {
                System.out.println("No restaurants found containing '" + input + "'.");
            } else {
