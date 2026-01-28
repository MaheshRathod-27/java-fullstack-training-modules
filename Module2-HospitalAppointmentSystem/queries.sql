-- Switch to hospital_db so all operations run on this database
USE hospital_db;

--1) INNER JOIN to fetch appointment details along with patient name and doctor information
SELECT
    a.appointment_id,
    a.appointment_date,
    a.start_time,
    a.end_time,
    p.full_name AS patient_name,
    d.full_name AS doctor_name,
    d.specialization
FROM appointment a
JOIN patient p ON a.patient_id = p.patient_id
JOIN doctor d ON a.doctor_id = d.doctor_id;


--2) Subquery to find doctors having more than N appointments on a specific date
SELECT
    d.doctor_id,
    d.full_name
FROM doctor d
WHERE d.doctor_id IN (
    SELECT a.doctor_id
    FROM appointment a
    WHERE a.appointment_date = '2026-01-28'
    GROUP BY a.doctor_id
    HAVING COUNT(a.appointment_id) > 2
);
