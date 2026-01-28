package com.example.optionaluserprofile;

public class Main {

    public static void main(String[] args) {

        // Below code shows bad design (null-based approach)
    	
//         UserProfileServiceBadDesign service = new UserProfileServiceBadDesign();
//         System.out.println(service.findByUserId("U1").getEmail());

    	
        // Using the Optional-based service (good design)
    	
        UserProfileService service = new UserProfileService();

        service.findByUserId("U2")
            // map() runs only if user is present
            .map(user -> {

                // Printing user details safely
                System.out.println("User Found:");
                System.out.println("ID    : " + user.getUserId());
                System.out.println("Name  : " + user.getName());
                System.out.println("Email : " + user.getEmail());
                System.out.println("Phone : " + user.getPhoneNumber().orElse("Not Available"));

                // Returning user because map() expects a return value
                return user;
            })

            // If user is not found, exception is thrown
            .orElseThrow(() -> new RuntimeException("-----User not found------"));
    }
}
