CREATE TABLE IF NOT EXISTS USER (
  id                BIGINT(20)   NOT NULL PRIMARY KEY AUTO_INCREMENT,
  username          VARCHAR(50)  NOT NULL,
  password          VARCHAR(100) NOT NULL,
  first_name        VARCHAR(50)  NOT NULL,
  last_name         VARCHAR(50)  NOT NULL,
  birthday          DATETIME     NOT NULL,
  age               INTEGER(3)   NOT NULL,
  gender            VARCHAR(6)   NOT NULL,
  registration_date DATETIME     NOT NULL,
  is_admin          TINYINT(1)   NOT NULL,
  is_vip            TINYINT(1)   NOT NULL,
  is_test           TINYINT(1)   NOT NULL,
  is_suspended      TINYINT(1)   NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
