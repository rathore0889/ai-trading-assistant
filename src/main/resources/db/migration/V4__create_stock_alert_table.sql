CREATE TABLE stock_alerts
(
    id BIGSERIAL PRIMARY KEY,

    symbol VARCHAR(20) NOT NULL,

    target_price NUMERIC(15,2) NOT NULL,

    condition VARCHAR(30) NOT NULL,

    triggered BOOLEAN NOT NULL DEFAULT FALSE,

    user_id BIGINT NOT NULL,

    CONSTRAINT fk_stock_alert_user
        FOREIGN KEY(user_id)
        REFERENCES users(id)
);