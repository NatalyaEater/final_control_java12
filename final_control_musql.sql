-- Создать таблицы с иерархией из диаграммы в БД
use human_friends;

CREATE TABLE class_animals
(
	Id INT AUTO_INCREMENT PRIMARY KEY, 
	Class_name VARCHAR(20)
);

INSERT INTO class_animals (Class_name)
VALUES ('pets'), ('pack_animals'); 

   
CREATE TABLE pets
(
	Id INT AUTO_INCREMENT PRIMARY KEY,
    Type_name VARCHAR (20),
    Class_id INT,
    FOREIGN KEY (Class_id) REFERENCES class_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO pets (Type_name, Class_id)
VALUES ('Dogs', 1),
('Cats', 1),  
('Hamsters', 1); 

CREATE TABLE pack_animals
(
	Id INT AUTO_INCREMENT PRIMARY KEY,
    Type_name VARCHAR (20),
    Class_id INT,
    FOREIGN KEY (Class_id) REFERENCES class_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO pack_animals (Type_name, Class_id)
VALUES ('Horses', 2),
('Camels', 2),  
('Donkeys', 2); 
 
-- 9. Заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения
CREATE TABLE dogs 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    Type_id int,
    Foreign KEY (Type_id) REFERENCES pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO dogs (Name, Birthday, Commands, Type_id)
VALUES ('Рекс', '2022-02-20', 'лежать,сидеть, лапу,голос', 1),
('Джек', '2020-03-30', 'лежать,сидеть, лапу,голос', 1),  
('Цезарь', '2021-01-21', 'лежать,сидеть, лапу,голос', 1);

CREATE TABLE cats 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    Type_id int,
    Foreign KEY (Type_id) REFERENCES pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO cats (Name, Birthday, Commands, Type_id)
VALUES ('Роза', '2020-05-25', 'кс-кс', 2),
('Василиса', '2021-07-10', 'кс-кс', 2),  
('Барсик', '2019-09-19', 'кс-кс', 2); 


CREATE TABLE hamsters 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    Type_id int,
    Foreign KEY (Type_id) REFERENCES pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO hamsters (Name, Birthday, Commands, Type_id)
VALUES ('Тобик', '2022-07-07', 'ап,скорее прячь', 3),
('Лися', '2021-11-20', 'ап,скорее прячь', 3),   
('Бубочка', '2021-03-01', 'ап,скорее прячь', 3);


CREATE TABLE horses 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    Type_id int,
    Foreign KEY (Type_id) REFERENCES pack_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO horses (Name, Birthday, Commands, Type_id)
VALUES ('Рысак', '2017-06-17', 'рысцой, шагом, бррр', 1),
('Орел', '2019-05-19', 'рысцой, шагом, бррр', 1),  
('Ярость', '2018-08-18', 'рысцой, шагом, бррр', 1);

CREATE TABLE camels 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    Type_id int,
    Foreign KEY (Type_id) REFERENCES pack_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO camels (Name, Birthday, Commands, Type_id)
VALUES ('Агата', '2010-01-20', 'стоять,пошли', 2),
('Аида', '2015-04-15', 'стоять,пошли', 2),  
('Чайна', '2012-09-12', 'стоять,пошли', 2);

CREATE TABLE donkeys 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    Type_id int,
    Foreign KEY (Type_id) REFERENCES pack_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO donkeys (Name, Birthday, Commands, Type_id)
VALUES ('Садко', '2017-03-17', 'стоять,пошли', 3),
('Ходжа', '2019-07-01', 'стоять,пошли', 3),  
('Бригелла', '2021-10-21', 'стоять,пошли', 3);

-- 10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.
SET SQL_SAFE_UPDATES = 0;
DELETE FROM camels;
SELECT Name, Birthday, Commands FROM horses
UNION SELECT  Name, Birthday, Commands FROM donkeys;

-- 11. Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года, но младше 3 лет.
-- В отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице 

CREATE TEMPORARY TABLE animals AS 
SELECT *, 'Dogs' AS genus FROM dogs
UNION SELECT *, 'Cats' AS genus FROM cats
UNION SELECT *, 'Hamsters' AS genus FROM hamsters
UNION SELECT *, 'Horses' as genus FROM horses
UNION SELECT *, 'Donkeys' AS genus FROM donkeys;

CREATE TABLE young_animals AS
SELECT Name, Birthday, genus, TIMESTAMPDIFF(MONTH, Birthday, CURDATE()) AS Age_in_month
FROM animals 
WHERE Birthday BETWEEN DATE_SUB(curdate(), INTERVAL 3 YEAR) AND DATE_SUB(CURDATE(), INTERVAL 1 YEAR);
 
SELECT * FROM young_animals;

-- 12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам.

CREATE TABLE all_animals (
id INT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  name VARCHAR(50) NOT NULL,
  Commands VARCHAR(100) NOT NULL,
  Birthday DATE NOT NULL,
  Type_id INT UNSIGNED NOT NULL,
  source_table VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO all_animals (name, Commands, Birthday, Type_id, source_table)
SELECT name, Commands, Birthday, Type_id, 'dogs' AS source_table
FROM dogs;
INSERT INTO all_animals (name, Commands, Birthday, Type_id, source_table)
SELECT name, Commands, Birthday, Type_id, 'cats' AS source_table
FROM cats;

INSERT INTO all_animals (name, Commands, Birthday, Type_id, source_table)
SELECT name, Commands, Birthday, Type_id, 'hamsters' AS source_table
FROM hamsters;

INSERT INTO all_animals (name, Commands, Birthday, Type_id, source_table)
SELECT name, Commands, Birthday, Type_id, 'horses' AS source_table
FROM horses;

INSERT INTO all_animals (name, Commands, Birthday, Type_id, source_table)
SELECT name, Commands, Birthday, Type_id, 'donkeys' AS source_table
FROM donkeys;

select*from all_animals;
