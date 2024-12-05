ALTER TABLE books
    ADD price_per_rental DECIMAL;

UPDATE books SET price_per_rental = price;

ALTER TABLE books
    ADD updated_on TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE books
    ALTER COLUMN price_per_rental SET NOT NULL;