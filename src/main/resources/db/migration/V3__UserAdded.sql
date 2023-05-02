CREATE TABLE "users"
(
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (username)
);

ALTER TABLE boxes
    ADD username VARCHAR(50),
    ADD CONSTRAINT FK_USER FOREIGN KEY (username) REFERENCES "users" (username);
