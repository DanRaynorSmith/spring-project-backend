DROP TABLE IF EXISTS `stock` CASCADE;

CREATE TABLE `stock` (
     `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
     `description` varchar(255),
     `category` varchar(255),
     `stock_count` int
);
