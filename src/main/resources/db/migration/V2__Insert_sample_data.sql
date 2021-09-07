INSERT INTO country (name) VALUES ('Spain');
INSERT INTO country (name) VALUES ('Italy');
INSERT INTO country (name) VALUES ('France');
INSERT INTO country (name) VALUES ('Australia');
INSERT INTO country (name) VALUES ('Portugal');
INSERT INTO country (name) VALUES ('Germany');
INSERT INTO country (name) VALUES ('Qatar');
INSERT INTO country (name) VALUES ('Netherlands');

INSERT INTO rider (name, bike_number, country_id) VALUES ('Fabio Quartararo', 20, 3);
INSERT INTO rider (name, bike_number, country_id) VALUES ('Johann Zarco', 5, 3);
INSERT INTO rider (name, bike_number, country_id) VALUES ('Francesco Bagnaia', 63, 2);
INSERT INTO rider (name, bike_number, country_id) VALUES ('Joan Mir', 36, 1);
INSERT INTO rider (name, bike_number, country_id) VALUES ('Jack Miller', 43, 4);
INSERT INTO rider (name, bike_number, country_id) VALUES ('Miguel Oliveira', 88, 5);
INSERT INTO rider (name, bike_number, country_id) VALUES ('Valentino Rossi', 46, 2);
INSERT INTO rider (name, bike_number, country_id) VALUES ('Marc Marquez', 93, 1);
INSERT INTO rider (name, bike_number, country_id) VALUES ('Maverick Vinales', 12, 1);

INSERT INTO grand_prix (name, country_id, winning_rider_id) VALUES ('Doha Grand Prix', 7, 9);
INSERT INTO grand_prix (name, country_id, winning_rider_id) VALUES ('Portimao Grand Prix', 5, 1);
INSERT INTO grand_prix (name, country_id, winning_rider_id) VALUES ('Jerez Grand Prix', 1, 5);
INSERT INTO grand_prix (name, country_id, winning_rider_id) VALUES ('Le Mans Grand Prix', 3, 5);
INSERT INTO grand_prix (name, country_id, winning_rider_id) VALUES ('Mugello Grand Prix', 2, 1);
INSERT INTO grand_prix (name, country_id, winning_rider_id) VALUES ('Catalan Grand Prix', 1, 6);
INSERT INTO grand_prix (name, country_id, winning_rider_id) VALUES ('Sachsenring Grand Prix', 6, 8);
INSERT INTO grand_prix (name, country_id, winning_rider_id) VALUES ('Assen Grand Prix', 8, 1);