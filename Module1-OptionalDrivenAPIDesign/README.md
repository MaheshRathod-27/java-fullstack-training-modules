# Module 1 – Optional Driven API Design (Java)

## 📌 Description
This project demonstrates how to design APIs using Java Optional instead of returning null.
It uses an in-memory User Profile service to show safe handling of absent data.

## 🎯 Learning Objective
- Understand problems with returning null
- Learn how to use Optional properly
- Design clean and safe service APIs

## 🛠️ Technologies Used
- Java (JDK 8 or above)
- Eclipse IDE
- Git & GitHub

## 💻 Software Requirements
- Java JDK 8+
- Eclipse IDE (or IntelliJ)
- Git (optional, for version control)

## ▶️ How to Run
1. Clone the repository
2. Open the project in Eclipse
3. Run `Main.java`
4. Check console output

## 📂 Project Structure
- UserProfile → Model class
- UserProfileService → Optional-based service
- UserProfileServiceBadDesign → Null-based bad design
- Main → Entry point and Optional usage

## ✅ Key Concepts Covered
- Optional.of()
- Optional.empty()
- map()
- orElse()
- orElseThrow()
- ifPresentOrElse()

## 🧠 Outcome
This module helps understand why Optional is better than null and how to design safer APIs.
