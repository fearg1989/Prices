CREATE TABLE prices (
    prices_id BIGINT PRIMARY KEY,
    brand_id BIGINT NOT NULL,
    curr VARCHAR(255) NOT NULL,
    end_date TIMESTAMP NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    price_list BIGINT NOT NULL,
    priority BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    start_date TIMESTAMP NOT NULL
);