INSERT INTO `ims`.`customers` (`first_name`, `surname`, `email`) VALUES ('jordan', 'harrison','jh@qa.com'),
('nikolett', 'bajna','nb@qa.com'),
('john', 'smith','js@gmail.com'),
('julie', 'hudson','jhudson@gmail.com');

INSERT INTO `ims`.`items` (`item_name`, `category`, `price`) VALUES ('ball', 'equipment', 2.33),
('monopoly', 'boardgame', 29.55),
('teddy bear','toy', 11.99);

INSERT INTO `ims`.`orders` (`customerID`) VALUES (1),
(3),
(1);

INSERT INTO `ims`.`orderitem` (`itemID`, `orderID`) VALUES (1, 1),
(1, 1),
(2, 1),
(3, 2);