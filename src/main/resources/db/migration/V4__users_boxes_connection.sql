ALTER TABLE boxes
    ADD user_id INTEGER;

ALTER TABLE boxes
    ADD CONSTRAINT FK_BOXES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);