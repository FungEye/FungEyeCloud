ALTER TABLE measured_conditions
    ADD co2 DOUBLE PRECISION;

ALTER TABLE measured_conditions
    ADD light DOUBLE PRECISION;

ALTER TABLE grows
    ADD CONSTRAINT FK_GROWS_ON_BOX FOREIGN KEY (box_id) REFERENCES boxes (id) ON DELETE CASCADE;