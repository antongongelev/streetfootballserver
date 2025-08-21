CREATE TABLE IF NOT EXISTS player
(
    id                 SERIAL      NOT NULL,
    telegram_id        BIGINT      NOT NULL,
    nickname           VARCHAR(30) NOT NULL,
    male               BOOLEAN     NOT NULL DEFAULT TRUE,
    birth_date         DATE        NOT NULL,
    primary_position   TEXT        NOT NULL,
    secondary_position TEXT        NULL,
    avatar             TEXT        NULL,
    created_at         TIMESTAMPTZ NOT NULL,

    CONSTRAINT pk_player_id PRIMARY KEY (id),
    CONSTRAINT uk_player_telegram_id UNIQUE (telegram_id)
);

CREATE INDEX player_telegram_id_idx ON player (telegram_id);
