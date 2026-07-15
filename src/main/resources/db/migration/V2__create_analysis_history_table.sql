CREATE TABLE analysis_history
(
    id BIGSERIAL PRIMARY KEY,

    symbol VARCHAR(20) NOT NULL,

    buy_price DOUBLE PRECISION,

    current_price DOUBLE PRECISION,

    quantity INTEGER,

    recommendation VARCHAR(50),

    confidence INTEGER,

    risk VARCHAR(50),

    reason VARCHAR(3000),

    created_at TIMESTAMP,

    user_id BIGINT,

    CONSTRAINT fk_analysis_history_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);