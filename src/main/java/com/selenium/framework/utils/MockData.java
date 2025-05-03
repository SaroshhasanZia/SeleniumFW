package com.selenium.framework.utils;

import java.util.Random;

public class MockData {
    private static final Random random = new Random();
    
    // Login credentials
    public static final String VALID_USERNAME = "tomsmith";
    public static final String VALID_PASSWORD = "SuperSecretPassword!";
    public static final String INVALID_USERNAME = "invalid_user";
    public static final String INVALID_PASSWORD = "invalid_password";
    
    // User data
    public static class User {
        public static String getRandomEmail() {
            return "user" + random.nextInt(1000) + "@test.com";
        }
        
        public static String getRandomFirstName() {
            String[] firstNames = {"John", "Jane", "Michael", "Sarah", "David", "Emily"};
            return firstNames[random.nextInt(firstNames.length)];
        }
        
        public static String getRandomLastName() {
            String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia"};
            return lastNames[random.nextInt(lastNames.length)];
        }
        
        public static String getRandomPhoneNumber() {
            return String.format("(%03d) %03d-%04d", 
                random.nextInt(1000), 
                random.nextInt(1000), 
                random.nextInt(10000));
        }
    }
    
    // Address data
    public static class Address {
        public static String getRandomStreet() {
            String[] streets = {"Main St", "Oak Ave", "Pine St", "Maple Dr", "Cedar Ln"};
            return random.nextInt(9999) + " " + streets[random.nextInt(streets.length)];
        }
        
        public static String getRandomCity() {
            String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix"};
            return cities[random.nextInt(cities.length)];
        }
        
        public static String getRandomState() {
            String[] states = {"NY", "CA", "IL", "TX", "AZ"};
            return states[random.nextInt(states.length)];
        }
        
        public static String getRandomZipCode() {
            return String.format("%05d", random.nextInt(100000));
        }
    }
} 