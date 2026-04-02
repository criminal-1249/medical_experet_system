CREATE DATABASE IF NOT EXISTS medical_diagnosis_db;
USE medical_diagnosis_db;

-- SYMPTOMS
CREATE TABLE symptoms (
    symptom_id INT AUTO_INCREMENT PRIMARY KEY,
    symptom_name VARCHAR(60) UNIQUE NOT NULL
);

-- DISEASES
CREATE TABLE diseases (
    disease_id INT AUTO_INCREMENT PRIMARY KEY,
    disease_name VARCHAR(60) UNIQUE NOT NULL,
    description VARCHAR(255)
);

-- DISEASE-SYMPTOM MAPPING
CREATE TABLE disease_symptom (
    id INT AUTO_INCREMENT PRIMARY KEY,
    disease_id INT NOT NULL,
    symptom_id INT NOT NULL,
    FOREIGN KEY (disease_id) REFERENCES diseases(disease_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (symptom_id) REFERENCES symptoms(symptom_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (disease_id, symptom_id)
);

-- TREATMENTS
CREATE TABLE treatments (
    treatment_id INT AUTO_INCREMENT PRIMARY KEY,
    disease_id INT NOT NULL,
    medication VARCHAR(100),
    advice VARCHAR(200),
    FOREIGN KEY (disease_id) REFERENCES diseases(disease_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

