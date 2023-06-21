DROP TABLE IF EXISTS patient;

CREATE TABLE patient(
    id INT AUTO_INCREMENT PRIMARY KEY,
    prenom VARCHAR(255),
    nom VARCHAR(255),
    date_de_naissance DATE,
    genre ENUM('F', 'M') NOT NULL,
    adresse_postale VARCHAR(255),
    numero_de_telephone VARCHAR(20)
);

INSERT INTO patient (prenom, nom, date_de_naissance, genre, adresse_postale, numero_de_telephone) VALUES
('Lucas', 'Ferguson', '1952-09-27', 'F', '745 West Valley Farms Drive', '628-423-0993'),
('Pippa', 'Rees', '1952-11-11', 'M', '599 East Garden Ave', '123-727-2779'),
('Edward', 'Arnold', '1946-11-26', 'M', '894 Hall Street', '451-761-8383'),
('Anthony', 'Sharp', '1958-06-29', 'F', '4 Southampton Road', '802-911-9975'),
('Wendy', 'Ince', '1949-12-07', 'F', '40 Sulphur Springs Dr', '131-396-5049'),
('Tracey', 'Wilson', '1966-12-31', 'F', '12 Cobblestone St', '300-452-1091'),
('Claire', 'Buckland', '1945-06-24', 'M', '193 Vale St', '833-534-0864'),
('Max', 'Clark', '1964-06-18', 'F', '12 Beechwood Road', '241-467-9197'),
('Natalie', 'Bailey', '1959-06-28', 'M', '1202 Bumble Dr', '747-815-0557');