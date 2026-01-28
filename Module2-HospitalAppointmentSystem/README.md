# Hospital Appointment Scheduling System

## Overview
This project implements a hospital appointment scheduling system using MySQL 8.  
It manages patients, doctors, and appointments while ensuring data integrity and preventing overlapping appointments.

## Database Design
- **patient**: Stores patient details
- **doctor**: Stores doctor details
- **appointment**: Links patients and doctors with appointment date and time

The design follows normalization principles to avoid data redundancy.

## Constraints
- Foreign key constraints enforce valid patient and doctor references
- CHECK constraint ensures that appointment start time is earlier than end time
- A BEFORE INSERT trigger prevents overlapping appointments for the same doctor on the same date

## Indexing
- **idx_appointment_date**: Optimizes date-based appointment searches
- **idx_appointment_doctor**: Optimizes doctor-wise appointment lookups and availability checks

**Trade-off:** Indexes improve read performance but slightly slow down insert, update, and delete operations.

## Queries
- JOIN query retrieves appointment details along with patient and doctor information
- Subquery identifies doctors with more than N appointments on a given day

## Technologies Used
- MySQL 8
- SQL
- Visual Studio Code
- Git & GitHub

## How to Execute the SQL Files

### Prerequisites
- MySQL 8
- MySQL Workbench
- Visual Studio Code (optional, for viewing/editing SQL files)

### Steps to Execute
1. Clone the repository or download it as a ZIP file.
2. Open the project folder in Visual Studio Code.
3. Navigate to the module folder containing the SQL files:
   - `schema.sql`
   - `indexes.sql`
   - `queries.sql`
4. Open **MySQL Workbench** and connect to your local MySQL server.
5. From the top menu, select **File → Open SQL Script**.
6. Open `schema.sql` and click the **Execute (⚡)** button.
   - This creates the database, tables, constraints, and trigger.
7. Repeat the same steps for:
   - `indexes.sql` (to create indexes)
   - `queries.sql` (to execute and view query results)
