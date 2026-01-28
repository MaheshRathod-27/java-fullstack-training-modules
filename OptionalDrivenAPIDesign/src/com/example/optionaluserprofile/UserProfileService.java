package com.example.optionaluserprofile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// Service class that handles user-related operations and Acts like an in-memory database using Map
public class UserProfileService {

    // Map is used to store users with userId as key
    private Map<String, UserProfile> users = new HashMap<>();

    // Constructor is used to add some sample users into memory
    public UserProfileService() {

        users.put("U1",new UserProfile("U1", "Mahesh", "mahesh@gmail.com", Optional.of("9876543210")));

        users.put("U2",new UserProfile("U2", "karthik", "karthik@gmail.com", Optional.empty()));

        users.put("U3",new UserProfile("U3", "Naveem", "Naveem@gmail.com", Optional.empty()));

        users.put("U4",new UserProfile("U4", "Vijay", "Vijay@gmail.com", Optional.of("987456770")));
    }

    // This method searches user by userId
    // It returns Optional to avoid returning null
    public Optional<UserProfile> findByUserId(String userId) {

        // ofNullable converts null into Optional.empty()
        return Optional.ofNullable(users.get(userId));
    }
}
