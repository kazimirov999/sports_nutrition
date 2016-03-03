INSERT INTO `countries` VALUES (1,'UA','Ukraine'),(2,'ge','Germany'),(3,'ta','China');

INSERT INTO `categories` VALUES (1,'amino category','','Amino'),(2,'vitamin catagory','','Vitamins'),(3,'geiners category','','Geiners');

INSERT INTO `customers` VALUES (1,'Vilna,3','ivan@ukr.net','Victor',false,'Fain','11',1),(2,'sdfsdf','nagirn@ukr.net','Bogdan',false,'Volkov','0635556421',1),(3,'Vinnitsa, Soborna str,95','vasia@ukr.net','Vasil',false,'Kopniak','0739874585',1);

INSERT INTO `brands` VALUES (1,'','ActivLab',1),(2,'','Allmax Nutrition',2),(3,'','Ultimate Nutrition',3),(4,'','Universal Nutrition',1),(5,'','USP labs',1),(25,'','NotLabs',3);

INSERT INTO `carts` VALUES (1,'2016-02-24 12:43:19',1025.00,'03002967',1),(2,'2016-02-24 12:55:01',3132.00,'48025693',2);

INSERT INTO `discounts` VALUES (1,'2050-05-31 00:00:00','Summer',0.00),(2,'2016-05-31 00:00:00','Spring',25.00);

INSERT INTO `tastes` VALUES (1,'Apple'),(2,'Banana'),(3,'Chocolate'),(20,'Malina');

INSERT INTO `products` VALUES (1,5520193,'somecomposition','somedesc','POWDER','some desc','WOMAN',null, 'Super Amino',0.00,'300 г',0,1,1,1),
(2,2220193,'some composition','some desc Super BCA','FLUIDE','some full descSuper BCA','UNISEX',null, 'Super BCA',500.50,'300 г',60,2,2,2),
(3,1120193,'some composition Super Geiner','some desc Super Geiner','CAPSULE','some full descSuper Geiner','UNISEX',null, 'Super Geiner',500.50,'300 г',40,2,3,1),
(4,3320193,'some composition','some desc SuperMEGAgeiner','TABLETS','some full desc SuperMEGAgeiner','UNISEX',null, 'Super MEGAgeiner',600.50,'90 шт',30,3,3,2);

INSERT INTO `product_taste_join` VALUES (1,1),(2,1),(3,2);

INSERT INTO `cart_items` VALUES (1,2,330.00,1,1,1),(2,3,95.00,2,1,1),(3,1,237.00,3,2,2);

INSERT INTO `users` VALUES (1,'Vinnitsa','2016-02-22 00:54:24','true','Alexandr','Kazimirov','kazimirov@ro.ru','4a7d1ed414474e4033ac29ccb8653d9b','0968314584','ROLE_ADMIN',1),(2,'Vinnitsa,Lenina 28','2016-02-24 12:45:00','true','Easy','User','simpleUser@mail.ru','b59c67bf196a4758191e42f76670ceba','0965551215','ROLE_USER',1);


