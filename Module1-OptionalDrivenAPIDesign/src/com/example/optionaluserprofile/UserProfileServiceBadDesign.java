package com.example.optionaluserprofile;

import java.util.HashMap;
import java.util.Map;

// This class shows BAD design on purpose
public class UserProfileServiceBadDesign {

    // Map is used as in-memory storage
    private Map<String, UserProfile> users = new HashMap<>();

    // Constructor adds some users
    // Notice that phoneNumber is passed as null (bad practice)
    public UserProfileServiceBadDesign() {
        users.put("U1", new UserProfile("U1", "Mahesh", "mahesh@gmail.com", null));
        users.put("U2", new UserProfile("U2", "Ravi", "ravi@gmail.com", null));
    }

    // This method returns UserProfile directly
    public UserProfile findByUserId(String userId) {

        // If user exists, return user object
        if (users.containsKey(userId)) {
            return users.get(userId);
        }

        // If user does not exist, returning null can cause NullPointerException
        return null;
    }
}
