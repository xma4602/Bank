CREATE TABLE IF NOT EXISTS account
(
    id UUID PRIMARY KEY,
    client_id uuid,
    number_account BIGINT,
    amount MONEY CHECK (amount > 0)
);

CREATE TABLE IF NOT EXISTS client
(
    id UUID PRIMARY KEY,
    number_client BIGINT,
    name VARCHAR(50)
    );