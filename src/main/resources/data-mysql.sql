INSERT INTO role (name) VALUES ("CUSTOMER");
INSERT INTO role (name) VALUES ("AUDITOR");

INSERT INTO permission (name) VALUES ("character:read-all");
INSERT INTO permission (name) VALUES ("character:read-detail");
INSERT INTO permission (name) VALUES ("comic:read-all");
INSERT INTO permission (name) VALUES ("comic:read-by-id");
INSERT INTO permission (name) VALUES ("user-interaction:read-my-interactions");
INSERT INTO permission (name) VALUES ("user-interaction:read-all");
INSERT INTO permission (name) VALUES ("user-interaction:read-by-username");

INSERT INTO granted_permission (role_id, permission_id) VALUES(1,1);
INSERT INTO granted_permission (role_id, permission_id) VALUES(1,2);
INSERT INTO granted_permission (role_id, permission_id) VALUES(1,3);
INSERT INTO granted_permission (role_id, permission_id) VALUES(1,4);
INSERT INTO granted_permission (role_id, permission_id) VALUES(1,5);

INSERT INTO granted_permission (role_id, permission_id) VALUES(2,1);
INSERT INTO granted_permission (role_id, permission_id) VALUES(2,2);
INSERT INTO granted_permission (role_id, permission_id) VALUES(2,3);
INSERT INTO granted_permission (role_id, permission_id) VALUES(2,4);
INSERT INTO granted_permission (role_id, permission_id) VALUES(2,5);
INSERT INTO granted_permission (role_id, permission_id) VALUES(2,6);
INSERT INTO granted_permission (role_id, permission_id) VALUES(2,7);

-- Usuario lgmarquez, pass = contrasena123
INSERT INTO user (username, password, role_id, account_expired, account_locked, credentials_expired, enabled) VALUES ('lmarquez', '$2a$10$ZK9Y2UfgLivxy37YPiAQWOLMsujY3XJHkRXGYz4Cv7trCZLPLqudi', 1, false, false, false, true);
-- Usuario gcanas, pass = contrasena456
INSERT INTO user (username, password, role_id, account_expired, account_locked, credentials_expired, enabled) VALUES ('gcanas', '$2a$10$juOXaule5VGy1KogEFCu5eFBSmZ54Wv0x1iIbaN7TpcouueD1epKy', 2, false, false, false, true);
