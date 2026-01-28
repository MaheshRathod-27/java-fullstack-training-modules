
-- Switch to hospital_db so all operations run on this database
USE hospital_db;

-- used index to speed up the date based appointment searches
CREATE INDEX idx_appointment_date
ON appointment (appointment_date);

-- used index to speed up the doctor based appointments
CREATE INDEX idx_appointment_doctor
ON appointment (doctor_id);
