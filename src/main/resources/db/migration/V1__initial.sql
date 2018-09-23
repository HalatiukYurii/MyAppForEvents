CREATE TABLE `events` (
  `id` binary(16) NOT NULL,
  `access` varchar(255) NOT NULL,
  `adress` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `name` varchar(50) NOT NULL,
  `organization` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `participation` (
  `id` binary(16) NOT NULL,
  `acess_organization` bit(1) NOT NULL,
  `acess_participant` bit(1) NOT NULL,
  `event_id` binary(16) DEFAULT NULL,
  `user_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs8gijahi7c4t1bunoqw4c4wre` (`event_id`),
  KEY `FKt6kjfjn4ns10i9qhuhwg7s8l` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `id` binary(16) NOT NULL,
  `email` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ow0gan20590jrb00upg3va2fn` (`login`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- INSERT INTO client_details (client_id, client_secret, authorities, authorized_grant_types, resource_ids, scope) VALUES
--   ('user-client', '{bcrypt}$2a$04$OQjKzCTOgHnsIE3Aq1YtM.M1.gyO30Dna06VKYsi85SAem5k0FiaS', 'TRUSTED_CLIENT', 'password',
--    'web-app', 'read,write');
--
--    INSERT INTO users (id, username, password) VALUES
--   (1, 'administrator',
--    '{bcrypt}$2a$10$kM0FeyUvvT3UrtEm3y/WAueojWeBO1vwGZFxXxdqfWZgZoMXvDeR.');
--
--

