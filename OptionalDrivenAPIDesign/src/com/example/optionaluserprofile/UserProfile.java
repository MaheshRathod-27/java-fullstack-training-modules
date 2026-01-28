package com.example.optionaluserprofile;

import java.util.Optional;
import java.util.regex.Pattern;

// This class represents a user profile
public class UserProfile {

	private String userId;
	private String name;
	private String email;
	
	// Phone number is optional, so we are using Optional
	private Optional<String> phoneNumber;

	// Constructor to create UserProfile object
	UserProfile(String userId, String name, String email, Optional<String> phoneNumber) {

		// Before storing email, we check if it is valid
		if (!isValidEmail(email)) {

			// If email format is wrong, we stop object creation
			// IllegalArgumentException is used because input is invalid
			throw new IllegalArgumentException("Invalid email format");
		}

		// If email is valid, assign values to class variables
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	// This method checks whether the given email is valid or not
	private boolean isValidEmail(String email) {

		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

		// Pattern.matches returns true if email matches the regex
		return Pattern.matches(emailRegex, email);
	}

	// Returns userId
	public String getUserId() {
		return userId;
	}

	// Returns user name
	public String getName() {
		return name;
	}

	// Returns email
	public String getEmail() {
		return email;
	}

	// Returns Optional phone number
	// If phone number is not present, Optional will be empty
	public Optional<String> getPhoneNumber() {
		return phoneNumber;
	}

}
