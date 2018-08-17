CREATE DATABASE xml_sax_dom_database;
USE xml_sax_dom_database;

CREATE TABLE trainer (
  id         BIGINT PRIMARY KEY AUTO_INCREMENT,
  name       VARCHAR(32) NOT NULL,
  language   VARCHAR(16) NOT NULL,
  experience INTEGER     NOT NULL
);
