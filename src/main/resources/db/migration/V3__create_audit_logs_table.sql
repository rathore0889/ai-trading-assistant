CREATE TABLE audit_logs
(
    id BIGSERIAL PRIMARY KEY,

    user_email VARCHAR(255),

    action VARCHAR(100),

    status VARCHAR(50),

    entity VARCHAR(100),

    details TEXT,

    ip_address VARCHAR(100),

    created_at TIMESTAMP
);