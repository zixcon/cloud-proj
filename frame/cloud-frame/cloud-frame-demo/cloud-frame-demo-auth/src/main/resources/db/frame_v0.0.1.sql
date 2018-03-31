
-- ----------------------------
-- Table structure for rc_menu
-- ----------------------------
DROP TABLE IF EXISTS `rc_menu`;
CREATE TABLE `rc_menu` (
  `id` varchar(64) NOT NULL ,
  `code` varchar(255) NOT NULL COMMENT '菜单编码',
  `p_code` varchar(255) NOT NULL COMMENT '菜单父编码',
  `p_id` varchar(255) NOT NULL COMMENT '父菜单ID',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `url` varchar(255) NOT NULL COMMENT '请求地址',
  `is_menu` int(11) NOT NULL COMMENT '是否是菜单',
  `level` int(11) NOT NULL COMMENT '菜单层级',
  `sort` int(11) NOT NULL COMMENT '菜单排序',
  `status` int(11) NOT NULL,
  `icon` varchar(255) DEFAULT '' ,
  `create_time` datetime DEFAULT CURRENT_TIME ,
  `update_time` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`),
  UNIQUE KEY `FK_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_menu
-- ----------------------------
INSERT INTO `rc_menu` VALUES ('000000000000000000', 'root', '0', '0', '系统根目录', '', '1', '0', '1', '1', null, '2017-08-03 18:31:54', null);
INSERT INTO `rc_menu` VALUES ('893287144657780736', 'system', 'root', '000000000000000000', '系统设置', 'system', '1', '1', '10', '1', '', '2017-08-04 09:47:06', null);
INSERT INTO `rc_menu` VALUES ('893288715881807872', 'userList', 'system', '893287144657780736', '用户管理', 'user/list', '1', '2', '1', '1', '', '2017-08-04 09:53:21', '2017-08-07 18:18:39');
INSERT INTO `rc_menu` VALUES ('893304960282787840', 'user/add', 'userList', '893288715881807872', '用户添加', 'user/add', '0', '3', '1', '1', '', '2017-08-04 10:57:54', '2017-08-08 11:02:55');
INSERT INTO `rc_menu` VALUES ('894396523532517376', 'user/edit', 'userList', '893288715881807872', '用户修改', 'user/edit', '0', '3', '1', '1', '', '2017-08-07 11:15:23', '2017-08-07 16:57:52');
INSERT INTO `rc_menu` VALUES ('894473486712438784', 'user/view', 'userList', '893288715881807872', '用户查看', 'user/View', '0', '3', '2', '1', '', '2017-08-07 16:21:12', null);
INSERT INTO `rc_menu` VALUES ('894473651837992960', 'user/delete', 'userList', '893288715881807872', '用户删除', 'user/delete', '0', '3', '4', '1', '', '2017-08-07 16:21:52', null);
INSERT INTO `rc_menu` VALUES ('894475142061621248', 'roleList', 'system', '893287144657780736', '角色管理', 'role/list', '1', '2', '2', '1', '', '2017-08-07 16:27:47', '2017-08-08 10:34:56');
INSERT INTO `rc_menu` VALUES ('894475827880656896', 'role/add', 'roleList', '894475142061621248', '角色添加', 'role/add', '0', '3', '1', '1', '', '2017-08-07 16:30:31', null);
INSERT INTO `rc_menu` VALUES ('894475985452269568', 'role/edit', 'roleList', '894475142061621248', '角色编辑', 'role/edit', '0', '3', '2', '1', '', '2017-08-07 16:31:08', null);
INSERT INTO `rc_menu` VALUES ('894476118730473472', 'role/delete', 'roleList', '894475142061621248', '角色删除', 'role/delete', '0', '3', '2', '1', '', '2017-08-07 16:31:40', '2017-08-07 16:37:24');
INSERT INTO `rc_menu` VALUES ('894476276402749440', 'role/permission', 'roleList', '894475142061621248', '角色配权', 'role/permission', '0', '3', '3', '1', '', '2017-08-07 16:32:18', null);
INSERT INTO `rc_menu` VALUES ('894476950951690240', 'menu/list', 'system', '893287144657780736', '菜单管理', 'menu/list', '1', '2', '2', '1', '', '2017-08-07 16:34:58', null);
INSERT INTO `rc_menu` VALUES ('894477107919323136', 'menu/add', 'menu/list', '894476950951690240', '菜单添加', 'menu/add', '0', '3', '1', '1', '', '2017-08-07 16:35:36', null);
INSERT INTO `rc_menu` VALUES ('894477244926263296', 'menu/edit', 'menu/list', '894476950951690240', '菜单编辑', 'menu/edit', '0', '3', '2', '1', '', '2017-08-07 16:36:08', null);
INSERT INTO `rc_menu` VALUES ('894477420512411648', 'menu/delete', 'menu/list', '894476950951690240', '菜单删除', 'menu/delete', '0', '3', '2', '1', '', '2017-08-07 16:36:50', null);
INSERT INTO `rc_menu` VALUES ('894477851082883072', 'apidoc', 'system', '893287144657780736', 'Api文档', 'swagger-ui.html', '1', '2', '9', '1', '', '2017-08-07 16:38:33', '2017-09-13 11:20:26');
INSERT INTO `rc_menu` VALUES ('894477995903811584', 'database/log', 'system', '893287144657780736', '数据库日志', 'druid', '1', '2', '10', '1', '', '2017-08-07 16:39:07', '2017-08-08 09:56:29');
INSERT INTO `rc_menu` VALUES ('894752734459199488', 'companyList', 'root', '000000000000000000', '公司管理', 'companyList', '1', '1', '1', '1', '', '2017-08-08 10:50:50', null);
INSERT INTO `rc_menu` VALUES ('903459378655395840', '/user/modify', 'userList', '893288715881807872', '密码重置', '/user/modify', '1', '3', '2', '1', '', '2017-09-01 11:27:56', null);

-- ----------------------------
-- Table structure for rc_privilege
-- ----------------------------
DROP TABLE IF EXISTS `rc_privilege`;
CREATE TABLE `rc_privilege` (
  `role_id` int(11) NOT NULL,
  `menu_id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_privilege
-- ----------------------------
INSERT INTO `rc_privilege` VALUES ('6', '893287144657780736', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '893288715881807872', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '893304960282787840', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894396523532517376', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894473486712438784', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894473651837992960', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894477851082883072', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894477995903811584', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('8', '893287144657780736', '2017-08-08 11:56:44');
INSERT INTO `rc_privilege` VALUES ('8', '893288715881807872', '2017-08-08 11:56:44');
INSERT INTO `rc_privilege` VALUES ('8', '893304960282787840', '2017-08-08 11:56:44');
INSERT INTO `rc_privilege` VALUES ('8', '894396523532517376', '2017-08-08 11:56:44');
INSERT INTO `rc_privilege` VALUES ('8', '894473486712438784', '2017-08-08 11:56:44');
INSERT INTO `rc_privilege` VALUES ('8', '894473651837992960', '2017-08-08 11:56:44');
INSERT INTO `rc_privilege` VALUES ('8', '894475142061621248', '2017-08-08 11:56:44');
INSERT INTO `rc_privilege` VALUES ('8', '894475827880656896', '2017-08-08 11:56:44');
INSERT INTO `rc_privilege` VALUES ('8', '894475985452269568', '2017-08-08 11:56:44');
INSERT INTO `rc_privilege` VALUES ('8', '894476118730473472', '2017-08-08 11:56:45');
INSERT INTO `rc_privilege` VALUES ('8', '894476276402749440', '2017-08-08 11:56:45');
INSERT INTO `rc_privilege` VALUES ('8', '894476950951690240', '2017-08-08 11:56:45');
INSERT INTO `rc_privilege` VALUES ('8', '894477107919323136', '2017-08-08 11:56:45');
INSERT INTO `rc_privilege` VALUES ('8', '894477244926263296', '2017-08-08 11:56:45');
INSERT INTO `rc_privilege` VALUES ('8', '894477420512411648', '2017-08-08 11:56:45');
INSERT INTO `rc_privilege` VALUES ('8', '894477851082883072', '2017-08-08 11:56:45');
INSERT INTO `rc_privilege` VALUES ('8', '894477995903811584', '2017-08-08 11:56:45');
INSERT INTO `rc_privilege` VALUES ('8', '894752734459199488', '2017-08-08 11:56:45');
INSERT INTO `rc_privilege` VALUES ('8', '894769217763540992', '2017-08-08 11:56:45');
INSERT INTO `rc_privilege` VALUES ('17', '893287144657780736', '2017-09-14 18:40:48');
INSERT INTO `rc_privilege` VALUES ('17', '894477851082883072', '2017-09-14 18:40:51');
INSERT INTO `rc_privilege` VALUES ('17', '894477995903811584', '2017-09-14 18:40:53');
INSERT INTO `rc_privilege` VALUES ('17', '894752734459199488', '2017-09-14 18:40:54');

-- ----------------------------
-- Table structure for rc_role
-- ----------------------------
DROP TABLE IF EXISTS `rc_role`;
CREATE TABLE `rc_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `tips` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_role_name` (`name`),
  UNIQUE KEY `unique_role_value` (`value`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_role
-- ----------------------------
INSERT INTO `rc_role` VALUES ('6', '管理员', 'admin', null, '2017-06-20 15:07:13', '2017-06-26 12:46:09', '1');
INSERT INTO `rc_role` VALUES ('8', '超级管理员', 'super', null, '2017-06-20 15:08:45', null, '1');
INSERT INTO `rc_role` VALUES ('17', '用户', 'user', null, '2017-06-28 18:50:39', '2017-07-21 09:41:28', '1');

-- ----------------------------
-- Table structure for rc_user
-- ----------------------------
DROP TABLE IF EXISTS `rc_user`;
CREATE TABLE `rc_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(96) DEFAULT NULL,
  `salt` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_user
-- ----------------------------
INSERT INTO `rc_user` VALUES ('46', null, 'super', '$2a$10$cKRbR9IJktfmKmf/wShyo.5.J8IxO/7YVn8twuWFtvPgruAF8gtKq', null, '超级管理员', '2017-06-22 14:26:09', '1', null, null, '1', '2017-06-20 15:12:16', '2017-09-12 14:39:48');
INSERT INTO `rc_user` VALUES ('48', null, 'admin', '$2a$10$cKRbR9IJktfmKmf/wShyo.5.J8IxO/7YVn8twuWFtvPgruAF8gtKq', null, '管理员', null, '1', null, null, '1', '2017-06-26 17:31:41', null);
INSERT INTO `rc_user` VALUES ('49', null, 'yangxiufeng', '$2a$10$cKRbR9IJktfmKmf/wShyo.5.J8IxO/7YVn8twuWFtvPgruAF8gtKq', null, '秀秀1', null, '1', null, null, '1', '2017-08-30 10:34:59', '2017-09-18 16:10:22');
INSERT INTO `rc_user` VALUES ('50', null, 'test1', '$2a$10$cKRbR9IJktfmKmf/wShyo.5.J8IxO/7YVn8twuWFtvPgruAF8gtKq', null, 'test1', null, '1', null, null, '1', '2017-09-18 16:11:15', null);
INSERT INTO `rc_user` VALUES ('51', null, 'test2', '$2a$10$cKRbR9IJktfmKmf/wShyo.5.J8IxO/7YVn8twuWFtvPgruAF8gtKq', null, 'test2', null, '1', null, null, '1', '2017-09-21 17:09:51', null);

-- ----------------------------
-- Table structure for rc_user_role
-- ----------------------------
DROP TABLE IF EXISTS `rc_user_role`;
CREATE TABLE `rc_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_user_role
-- ----------------------------
INSERT INTO `rc_user_role` VALUES ('1', '46', '8', '2017-09-11 13:02:45', null);
INSERT INTO `rc_user_role` VALUES ('2', '48', '6', '2017-09-11 13:02:56', null);
INSERT INTO `rc_user_role` VALUES ('3', '49', '17', '2017-09-11 13:03:12', null);
INSERT INTO `rc_user_role` VALUES ('19', '50', '6', '2017-09-12 14:20:20', '超级管理员');
INSERT INTO `rc_user_role` VALUES ('20', '50', '17', '2017-09-12 14:20:20', '超级管理员');
INSERT INTO `rc_user_role` VALUES ('22', '57', '8', '2017-09-18 16:34:58', '超级管理员');
INSERT INTO `rc_user_role` VALUES ('23', '57', '17', '2017-09-18 16:34:58', '超级管理员');
