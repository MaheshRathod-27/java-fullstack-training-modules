-- ===============================
-- DATABASE
-- ===============================
CREATE DATABASE IF NOT EXISTS hospital_db;
USE hospital_db;

-- ===============================
-- PATIENT TABLE
-- ===============================
CREATE TABLE patient (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL UNIQUE,
    email VARCHAR(100) UNIQUE
);

-- ===============================
-- DOCTOR TABLE
-- ===============================
CREATE TABLE doctor (
    doctor_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL
);

-- ===============================
-- APPOINTMENT TABLE
-- ===============================
CREATE TABLE appointment (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,

    -- Time validation
    CONSTRAINT chk_time CHECK (start_time < end_time),

    -- Foreign key constraints
    CONSTRAINT fk_patient
        FOREIGN KEY (patient_id)
        REFERENCES patient(patient_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    CONSTRAINT fk_doctor
        FOREIGN KEY (doctor_id)
        REFERENCES doctor(doctor_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- ===============================
-- TRIGGER: PREVENT OVERLAPPING APPOINTMENTS
-- ===============================
DELIMITER $$

CREATE TRIGGER prevent_overlapping_appointments
BEFORE INSERT ON appointment
FOR EACH ROW
BEGIN
    IF EXISTS (
        SELECT 1
        FROM appointment
        WHERE doctor_id = NEW.doctor_id
        AND appointment_date = NEW.appointment_date
        AND NEW.start_time < end_time
        AND NEW.end_time > start_time
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Overlapping appointment for the same doctor is not allowed';
    END IF;
END$$

DELIMITER ;
