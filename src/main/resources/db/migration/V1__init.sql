CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255),
    sex VARCHAR(50),
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_users_username (username)
);

CREATE TABLE permissions (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_permissions_name (name)
);

CREATE TABLE ent_subjects (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_permissions (
    user_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, permission_id),
    CONSTRAINT fk_user_permissions_user
        FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_user_permissions_permission
        FOREIGN KEY (permission_id) REFERENCES permissions (id)
);

CREATE TABLE user_subjects (
    user_id BIGINT NOT NULL,
    subject_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, subject_id),
    CONSTRAINT fk_user_subjects_user
        FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_user_subjects_subject
        FOREIGN KEY (subject_id) REFERENCES ent_subjects (id)
);

CREATE TABLE ent_results (
    id BIGINT NOT NULL AUTO_INCREMENT,
    exam_date DATE,
    score INT,
    user_id BIGINT,
    subject_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_ent_results_user
        FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_ent_results_subject
        FOREIGN KEY (subject_id) REFERENCES ent_subjects (id)
);