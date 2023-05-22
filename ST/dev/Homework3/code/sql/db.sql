  drop database if exists organizer;
  create database organizer;
  use organizer;

  CREATE TABLE `user` (
    `id` integer PRIMARY KEY AUTO_INCREMENT,
    `username` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    `fullname` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
    `email` varchar(255) NOT NULL
  );

  CREATE TABLE `document` (
    `id` integer PRIMARY KEY AUTO_INCREMENT,
    `title` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
    `content` text COLLATE  utf8_slovenian_ci NOT NULL,
    `status` varchar(255) NOT NULL,
    `author_id` integer,
    `created_at` timestamp
  );

  CREATE TABLE `role` (
    `id` integer PRIMARY KEY,
    `role` varchar(255) COLLATE utf8_slovenian_ci NOT NULL
  );

  CREATE TABLE `user_roles` (
    `id` integer PRIMARY KEY AUTO_INCREMENT,
    `user_id` integer,
    `role_id` integer
  );

  CREATE TABLE `title` (
    `id` integer PRIMARY KEY,
    `title` varchar(255) COLLATE utf8_slovenian_ci NOT NULL
  );

  CREATE TABLE `user_titles` (
    `id` integer PRIMARY KEY AUTO_INCREMENT,
    `user_id` integer,
    `title_id` integer
  );

  ALTER TABLE `document` ADD FOREIGN KEY (`author_id`) REFERENCES `user` (`id`);

  ALTER TABLE `user_roles` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

  ALTER TABLE `user_roles` ADD FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

  ALTER TABLE `user_titles` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

  ALTER TABLE `user_titles` ADD FOREIGN KEY (`title_id`) REFERENCES `title` (`id`);

  INSERT INTO `role` (`id`, `role`) VALUES (1, "admin");
  INSERT INTO `role` (`id`, `role`) VALUES (2, "user");

  INSERT INTO `title` (`id`, `title`) VALUES (1, "Direktor");
  INSERT INTO `title` (`id`, `title`) VALUES (2, "Vodja oddelka");
  INSERT INTO `title` (`id`, `title`) VALUES (3, "Zaposleni");

  INSERT INTO `user`(`id`, `username`, `password`, `fullname`, `email`) VALUES (1, "admin", "$2y$10$BefdLReafL9UQI0zhmuABumpl4usTOVIlK/0g0YDrGm2or.KdUNM.", "Admin Administratorski", "admin@docorg.com");

  INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (1, 1);