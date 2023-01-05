CREATE USER 'TomVe'@'localhost' IDENTIFIED BY 'pwd123';
CREATE DATABASE WEATHER;
use WEATHER;
 -- source ../worldcities.sql
CREATE TABLE temperatures (
    id INT PRIMARY KEY AUTO_INCREMENT,
    timesstamp DATE,
    city VARCHAR(49),
    temperature NUMERIC(4,2)
);

CREATE TABLE temperatures2 (
    id INT PRIMARY KEY AUTO_INCREMENT,
    timesstamp DATE,
    worldcity_id INT,
    temperature NUMERIC(4,2)
);
GRANT ALL PRIVILEGES ON weather.* TO 'TomVe'@'localhost';
