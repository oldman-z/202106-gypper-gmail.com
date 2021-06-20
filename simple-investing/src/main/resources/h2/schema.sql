DROP TABLE IF EXISTS product;
CREATE TABLE product(
	product_id               INT PRIMARY KEY,
	title                    VARCHAR(50),
	total_investing_amount   DECIMAL,
	current_investing_amount DECIMAL,
	status                   VARCHAR(10),
	started_at               TIMESTAMP,
	finished_at              TIMESTAMP,
	last_tid                 INT
);

DROP TABLE IF EXISTS investment_transaction;
CREATE TABLE investment_transaction(
	tid              INT PRIMARY KEY,
	transaction_date VARCHAR(8),
	transaction_time VARCHAR(6),
	customer_id      INT,
	product_id       INT,
	investing_amount DECIMAL,
	result_code      VARCHAR(4),
	result_message   VARCHAR(100)
);

CREATE INDEX idx_investment_transaction_01 ON investment_transaction(customer_id);

DROP SEQUENCE  IF EXISTS tid_sequence;
CREATE SEQUENCE  tid_sequence
    START WITH 1
    INCREMENT BY 1;
