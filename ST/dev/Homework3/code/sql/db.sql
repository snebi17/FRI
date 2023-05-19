drop database if exists organizer;
create database organizer;
use organizer;

CREATE TABLE IF NOT EXISTS `user` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fullname` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255)
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
  `id` integer PRIMARY KEY,
  `user_id` integer,
  `role_id` integer
);

CREATE TABLE `title` (
  `id` integer PRIMARY KEY,
  `title` varchar(255) COLLATE utf8_slovenian_ci NOT NULL
);

CREATE TABLE `user_titles` (
  `id` integer PRIMARY KEY,
  `user_id` integer,
  `title_id` integer
);

ALTER TABLE `document` ADD FOREIGN KEY (`author_id`) REFERENCES `user` (`id`);

ALTER TABLE `user_roles` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `user_roles` ADD FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

ALTER TABLE `user_titles` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `user_titles` ADD FOREIGN KEY (`title_id`) REFERENCES `title` (`id`);
