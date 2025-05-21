-- nyam_road.image definition
CREATE DATABASE nyam_road

CREATE TABLE `image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `thumb_file` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '썸네일 파일명',
  `comp_file` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '압축 파일명',
  `org_file` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '원본 파일명',
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '사용자 아이디',
  `use_yn` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Y',
  `rgst_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mdfc_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nyam_road.`user` definition

CREATE TABLE `user` (
  `user_id` varchar(40) NOT NULL,
  `name` varchar(40) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(40) NOT NULL,
  `role` enum('admin','user') DEFAULT 'user',
  `image_id` int DEFAULT NULL,
  `use_yn` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Y',
  `rgst_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mdfc_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `intro` varchar(400) DEFAULT NULL,
  `feed_type` enum('rec','plc','all') NOT NULL DEFAULT 'rec',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `email` (`email`),
  KEY `user_image_FK` (`image_id`),
  CONSTRAINT `user_image_FK` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nyam_road.article_master definition

CREATE TABLE `article_master` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(40) NOT NULL,
  `type` enum('eat','recipe') NOT NULL,
  `image_id` int NOT NULL,
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `ingredient` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `use_yn` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Y',
  `rgst_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mdfc_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `article_master_image_FK` (`image_id`),
  KEY `article_master_user_FK` (`user_id`),
  CONSTRAINT `article_master_image_FK` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`),
  CONSTRAINT `article_master_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nyam_road.follow definition

CREATE TABLE `follow` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(40) NOT NULL,
  `follow_id` varchar(40) NOT NULL,
  `use_yn` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Y',
  `rgst_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mdfc_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` enum('pending','accept','decline') NOT NULL DEFAULT 'pending',
  PRIMARY KEY (`id`),
  KEY `follow_idx_user_id` (`user_id`),
  KEY `follow_idx_follow_id` (`follow_id`),
  CONSTRAINT `follow_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- nyam_road.article_comment definition

CREATE TABLE `article_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `article_id` int NOT NULL,
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `use_yn` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Y',
  `rgst_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mdfc_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `article_comment_user_FK` (`user_id`),
  KEY `article_comment_article_master_FK` (`article_id`),
  CONSTRAINT `article_comment_article_master_FK` FOREIGN KEY (`article_id`) REFERENCES `article_master` (`id`),
  CONSTRAINT `article_comment_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nyam_road.article_detail definition

CREATE TABLE `article_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `article_id` int NOT NULL,
  `order` int NOT NULL,
  `image_id` int NOT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `use_yn` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Y',
  `rgst_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mdfc_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `article_detail_article_master_FK` (`article_id`),
  KEY `article_detail_image_FK` (`image_id`),
  CONSTRAINT `article_detail_article_master_FK` FOREIGN KEY (`article_id`) REFERENCES `article_master` (`id`),
  CONSTRAINT `article_detail_image_FK` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nyam_road.article_like definition

CREATE TABLE `article_like` (
  `id` int NOT NULL AUTO_INCREMENT,
  `article_id` int NOT NULL,
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `use_yn` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Y',
  `rgst_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mdfc_dttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `article_like_article_master_FK` (`article_id`),
  KEY `article_like_user_FK` (`user_id`),
  CONSTRAINT `article_like_article_master_FK` FOREIGN KEY (`article_id`) REFERENCES `article_master` (`id`),
  CONSTRAINT `article_like_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
