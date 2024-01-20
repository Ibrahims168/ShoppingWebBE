
CREATE TABLE user (
     id int(11) unsigned NOT NULL AUTO_INCREMENT,
     first_name VARCHAR(300) NOT NULL DEFAULT '',
     last_name VARCHAR(300) NOT NULL DEFAULT '',
     email VARCHAR(300) NOT NULL DEFAULT '',
     phone_number VARCHAR(20) NOT NULL DEFAULT '',
     address VARCHAR(300) NOT NULL DEFAULT '',
     username varchar(20) NOT NULL DEFAULT '',
     password varchar(20) NOT NULL DEFAULT '',
     PRIMARY KEY (id)
);

CREATE TABLE item (
    item_id int(11) unsigned NOT NULL AUTO_INCREMENT,
    item_title VARCHAR(300) NOT NULL DEFAULT '',
    item_img VARCHAR(300) NOT NULL DEFAULT '',
    item_price DOUBLE NOT NULL,
    item_stock INT NOT NULL,
    PRIMARY KEY (item_id)
);

CREATE TABLE USER_FAVORITE_LIST (
    user_id INT,
    item_id INT,
    PRIMARY KEY (user_id, item_id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (item_id) REFERENCES item(item_id)
);

CREATE TABLE ORDER_TABLE (
    order_id int(11) unsigned NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    order_date DATE,
    order_address VARCHAR(300) NOT NULL DEFAULT '',
    total_price DOUBLE NOT NULL,
    order_status VARCHAR(20) NOT NULL DEFAULT '',
    PRIMARY KEY (order_id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE USER_ORDER_ITEM_LIST (
    order_id INT,
    item_id INT,
    user_id INT,
    PRIMARY KEY (order_id, item_id, user_id),
    FOREIGN KEY (order_id) REFERENCES ORDER_TABLE(order_id),
    FOREIGN KEY (item_id) REFERENCES item(item_id),
    FOREIGN KEY (user_id) REFERENCES user(id)

);

INSERT INTO user (id, first_name, last_name, email, phone_number, address, username, password)
VALUES (1, 'ibrahim', 'sharif', 'ibrahim8@gmail.com', '0539832111', 'Jerusalem', 'ibrahim123', 'password');

INSERT INTO item (item_id, item_title, item_img, item_price, item_stock)
VALUES (1, 'Tuxedo Jacket', 'https://eu.louisvuitton.com/images/is/image/lv/1/PP_VP_L/louis-vuitton-single-breasted-wool-tuxedo-cut-away-jacket-ready-to-wear--HQFJ5WBFL900_PM2_Front%20view.png?wid=1090&hei=1090', 2299.0, 3);

INSERT INTO item (item_id, item_title, item_img, item_price, item_stock)
VALUES (2, 'Classic Wool Pants', 'https://eu.louisvuitton.com/images/is/image/lv/1/PP_VP_L/louis-vuitton-classic-wool-cigarette-pants-ready-to-wear--HQP60WFYD900_PM2_Front%20view.png?wid=1090&hei=1090', 1199.0, 1);

INSERT INTO item (item_id, item_title, item_img, item_price, item_stock)
VALUES (3, 'Embossed Turtle Neck', 'https://eu.louisvuitton.com/images/is/image/lv/1/PP_VP_L/louis-vuitton-lvse-lv-embossed-turtle-neck-ready-to-wear--HMN41WJZE900_PM2_Front%20view.png?wid=1090&hei=1090', 999.0, 41);

INSERT INTO item (item_id, item_title, item_img, item_price, item_stock)
VALUES (4, 'Tuxedo Coat', 'https://eu.louisvuitton.com/images/is/image/lv/1/PP_VP_L/louis-vuitton-double-breasted-wool-tuxedo-coat-ready-to-wear--HQFC1WYG3900_PM2_Front%20view.png?wid=1090&hei=1090', 3299.0, 1);

INSERT INTO item (item_id, item_title, item_img, item_price, item_stock)
VALUES (5, 'Blouson Jacket', 'https://eu.louisvuitton.com/images/is/image/lv/1/PP_VP_L/louis-vuitton-wool-and-leather-tracktop-ready-to-wear--HQN22WZLL633_PM2_Front%20view.png?wid=1090&hei=1090', 3850.0, 22);

INSERT INTO item (item_id, item_title, item_img, item_price, item_stock)
VALUES (6, 'Wool Bouclette Zipped', 'https://eu.louisvuitton.com/images/is/image/lv/1/PP_VP_L/louis-vuitton-monogram-wool-bouclette-zipped-blouson-ready-to-wear--HQFB3WY98651_PM2_Front%20view.png?wid=1090&hei=1090', 3299.0, 41);

INSERT INTO item (item_id, item_title, item_img, item_price, item_stock)
VALUES (7, 'Jet Pocket Blouson', 'https://eu.louisvuitton.com/images/is/image/lv/1/PP_VP_L/louis-vuitton-jet-pocket-blouson-ready-to-wear--HPFB1WUR8631_PM2_Front%20view.png?wid=1090&hei=1090', 4650.0, 15);

INSERT INTO item (item_id, item_title, item_img, item_price, item_stock)
VALUES (8, 'Hybrid Zipped Hoodie', 'https://eu.louisvuitton.com/images/is/image/lv/1/PP_VP_L/louis-vuitton-hybrid-zipped-signature-hoodie-ready-to-wear--HQY68WFWC900_PM2_Front%20view.png?wid=1090&hei=1090', 3299.0, 41);

INSERT INTO item (item_id, item_title, item_img, item_price, item_stock)
VALUES (9, 'LV Dark Night', 'https://eu.louisvuitton.com/images/is/image/lv/1/PP_VP_L/louis-vuitton-embroidered-wool-cardigan-ready-to-wear--HQN31WZLL900_PM2_Front%20view.png?wid=1090&hei=1090', 5450.0, 22);

INSERT INTO item (item_id, item_title, item_img, item_price, item_stock)
VALUES (10, 'Keepall Bandoulière', 'https://eu.louisvuitton.com/images/is/image/lv/1/PP_VP_L/louis-vuitton-keepall-bandouli%C3%A8re-45-monogram-eclipse-travel--M40569_PM2_Front%20view.png?wid=1090&hei=1090', 2500.0, 15);

INSERT INTO item (item_id, item_title, item_img, item_price, item_stock)
VALUES (11, 'Pont Neuf Jacket', 'https://eu.louisvuitton.com/images/is/image/lv/1/PP_VP_L/louis-vuitton-single-breasted-silk-blend-pont-neuf-jacket-ready-to-wear--HQFJ4WZD0900_PM2_Front%20view.png?wid=1090&hei=1090', 8299.0, 37);

INSERT INTO item (item_id, item_title, item_img, item_price, item_stock)
VALUES (12, 'Cashmere Crewneck', 'https://eu.louisvuitton.com/images/is/image/lv/1/PP_VP_L/louis-vuitton-monogram-mix-cashmere-crewneck-ready-to-wear--HON42WR93918_PM2_Front%20view.png?wid=1090&hei=1090', 850.0, 2);
