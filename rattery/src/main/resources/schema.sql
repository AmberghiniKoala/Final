DROP TABLE IF EXISTS rat_variety;
DROP TABLE IF EXISTS variety;
DROP TABLE IF EXISTS rat;
DROP TABLE IF EXISTS rattery;

CREATE TABLE rattery (
  rattery_id int NOT NULL AUTO_INCREMENT,
  business_name varchar(256) NOT NULL,
  street_address varchar(128),
  city varchar(64) NOT NULL,
  state varchar(64) NOT NULL,
  zip varchar(24),
  phone varchar(32),
  email varchar(128),
  website varchar(256),
  PRIMARY KEY(rattery_id)
);

CREATE TABLE rat (
  rat_id int NOT NULL AUTO_INCREMENT,
  rattery_id int NOT NULL,
  unique_id varchar(128) NOT NULL,
  gender varchar(8) NOT NULL,
  fixed boolean NOT NULL,
  age varchar(16) NOT NULL,
  price decimal(10,2),
  temperament varchar(256),
  mother_variety varchar(256),
  father_variety varchar(256),
  PRIMARY KEY(rat_id),
  FOREIGN KEY(rattery_id) REFERENCES rattery(rattery_id) ON DELETE CASCADE
);

CREATE TABLE variety (
  variety_id int NOT NULL AUTO_INCREMENT,
  variety_type varchar(128),
  PRIMARY KEY(variety_id)
);

CREATE TABLE rat_variety (
  rat_id int NOT NULL,
  variety_id int NOT NULL,
  FOREIGN KEY(rat_id) REFERENCES rat(rat_id) ON DELETE CASCADE,
  FOREIGN KEY(variety_id) REFERENCES variety(variety_id) ON DELETE CASCADE
);