INSERT INTO users (name, email, sex, username, password)
VALUES ('Pavel', 'aniconte@gmail.com', NULL, 'aniconte', '$2b$10$m7ObTOaoCKjilFElxtq8HeNlLxY1lgWtwolGMHy7UYMk6cPr8XLte')
ON DUPLICATE KEY UPDATE
  name = VALUES(name),
  email = VALUES(email),
  sex = VALUES(sex),
  password = VALUES(password);

SET @admin_id = (SELECT id FROM users WHERE username = 'aniconte');

INSERT IGNORE INTO user_permissions (user_id, permission_id)
SELECT @admin_id, p.id
FROM permissions p;