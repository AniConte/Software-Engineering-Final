INSERT IGNORE INTO permissions (name) VALUES
('ENT_SUBJECT_READ'),
('ENT_SUBJECT_WRITE'),
('ENT_RESULT_READ'),
('ENT_RESULT_WRITE');

SET @admin_id := (SELECT id FROM users WHERE username = 'aniconte');

INSERT IGNORE INTO user_permissions (user_id, permission_id)
SELECT @admin_id, p.id
FROM permissions p;