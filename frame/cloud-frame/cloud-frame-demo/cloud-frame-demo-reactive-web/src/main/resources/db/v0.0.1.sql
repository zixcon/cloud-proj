use bootdemo;

-- DROP TABLE IF EXISTS account_info;
CREATE TABLE IF NOT EXISTS account_info (
  id          INT(20)      NOT NULL AUTO_INCREMENT,
  user_name   VARCHAR(50)  NOT NULL,
  password    VARCHAR(100) NOT NULL,
  salt        VARCHAR(50)  NOT NULL,
  status      TINYINT(2)   NOT NULL DEFAULT 0,
  type        TINYINT(2)   NOT NULL DEFAULT 0,
  create_time DATETIME     NOT NULL DEFAULT current_timestamp COMMENT '注册时间',
  update_time DATETIME     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE (user_name)
)
  ENGINE = innodb
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8;


-- DROP TABLE IF EXISTS wx_app_info;
CREATE TABLE IF NOT EXISTS wx_app_info (
  id          INT(20)      NOT NULL AUTO_INCREMENT,
  appid       VARCHAR(100) NOT NULL,
  secret      VARCHAR(100) NOT NULL,
  enable      TINYINT(1)   NOT NULL DEFAULT 1,
  type        TINYINT(2)   NOT NULL DEFAULT 0,
  create_time DATETIME     NOT NULL DEFAULT current_timestamp COMMENT '注册时间',
  update_time DATETIME     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE (appid)
)
  ENGINE = innodb
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8;


-- DROP TABLE IF EXISTS wx_account_info;
CREATE TABLE IF NOT EXISTS wx_account_info (
  id          INT(20)      NOT NULL AUTO_INCREMENT,
  openid      VARCHAR(50)  NOT NULL DEFAULT "",
  unionid     VARCHAR(50)  NOT NULL DEFAULT "",
  create_time DATETIME     NOT NULL DEFAULT current_timestamp COMMENT '注册时间',
  update_time DATETIME     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE (openid)
)
  ENGINE = innodb
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8;
