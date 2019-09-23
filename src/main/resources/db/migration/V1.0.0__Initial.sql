CREATE TABLE quarkus_user (
    id UUID PRIMARY KEY,
    login VARCHAR(100),
    email VARCHAR(200),
    password VARCHAR(100),
    lastAccess TIMESTAMP,
    active BOOLEAN
);

INSERT INTO quarkus_user (id, login, email, password, active) VALUES ('3592317a-dbde-11e9-8a34-2a2ae2dbcce4', 'test', 'test@test.com', '1234', true);
INSERT INTO quarkus_user (id, login, email, password, active) VALUES ('359233f0-dbde-11e9-8a34-2a2ae2dbcce4', 'test1', 'test1@test.com', '1234', true);
INSERT INTO quarkus_user (id, login, email, password, active) VALUES ('3592354e-dbde-11e9-8a34-2a2ae2dbcce4', 'test2', 'test2@test.com', '1234', true);