INSERT INTO departments (name)VALUES ('РИО');
INSERT INTO departments (name)VALUES ('ОРКП');
INSERT INTO departments (name)VALUES ('ОМАИ');
INSERT INTO departments (name)VALUES ('СТУ');
INSERT INTO departments (name)VALUES ('НТБ');
INSERT INTO departments (name)VALUES ('ЦНЗК');
INSERT INTO departments (name)VALUES ('ЦНЗИ');
INSERT INTO departments (name)VALUES ('Музей');
INSERT INTO departments (name)VALUES ('Приёмная');
INSERT INTO departments (name)VALUES ('ОКИР');

INSERT INTO printers (model) VALUE ('XEROX 7525');
INSERT INTO printers (model) VALUE ('HP 5520');

INSERT INTO paper_density (density) VALUE ('80');
INSERT INTO paper_density (density) VALUE ('90');
INSERT INTO paper_density (density) VALUE ('120');
INSERT INTO paper_density (density) VALUE ('135');
INSERT INTO paper_density (density) VALUE ('160');
INSERT INTO paper_density (density) VALUE ('170');
INSERT INTO paper_density (density) VALUE ('200');
INSERT INTO paper_density (density) VALUE ('250');
INSERT INTO paper_density (density) VALUE ('300');

INSERT INTO paper_size (size) VALUE ('A3');
INSERT INTO paper_size (size) VALUE ('A4');
INSERT INTO paper_size (size) VALUE ('A5');

INSERT INTO role (role) VALUE ('Admin');
INSERT INTO role (role) VALUE ('User');

INSERT INTO status_work (work_status) VALUE ('Не готово');
INSERT INTO status_work (work_status) VALUE ('Готово!');

INSERT INTO type_of_paper (paper_type) VALUE ('Самоклейка');
INSERT INTO type_of_paper (paper_type) VALUE ('Баннер');
INSERT INTO type_of_paper (paper_type) VALUE ('Бумага');



# SELECT * FROM bid WHERE month(date) = MONTH(now())AND YEAR(date) = YEAR(NOW());
# SELECT * FROM bid WHERE month(date) = MONTH(DATE_ADD(NOW(), INTERVAL -1 MONTH))AND YEAR(date) = YEAR(now());