DROP TABLE IF EXISTS `ims`.`orderitem`;
DROP TABLE IF EXISTS `ims`.`orders`;
DROP TABLE IF EXISTS `ims`.`customers`;
DROP TABLE IF EXISTS `ims`.`items`;
CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NULL DEFAULT NULL,
    `surname` VARCHAR(40) NULL DEFAULT NULL,
    `email` VARCHAR(40) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(40) NULL DEFAULT NULL,
    `category` VARCHAR(40) NULL DEFAULT NULL,
    `price` double(11, 2) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `ims`.`orders` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `customerID` INT(11) NOT NULL,
    `dateTime` TIMESTAMP NULL DEFAULT NOW(),
    PRIMARY KEY (`id`),
    FOREIGN KEY (customerID) REFERENCES customers(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS `ims`.`orderitem` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `itemID` INT(11) NOT NULL,
    `orderID` INT(11) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (itemID) REFERENCES items(id) ON DELETE CASCADE,
    FOREIGN KEY (orderID) REFERENCES orders(id) ON DELETE CASCADE
);