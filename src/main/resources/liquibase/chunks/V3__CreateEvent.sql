CREATE TABLE IF NOT EXISTS event
(
    id           SERIAL      NOT NULL,
    stadium_id   BIGINT      NOT NULL,
    begin_at     TIMESTAMPTZ NOT NULL,
    is_finished  BOOLEAN     NOT NULL DEFAULT FALSE,
    is_cancelled BOOLEAN     NOT NULL DEFAULT FALSE,
    min_players  SMALLINT    NULL,
    max_players  SMALLINT    NULL,
    min_age      SMALLINT    NULL,
    max_age      SMALLINT    NULL,
    duration_min SMALLINT    NOT NULL DEFAULT 120,
    created_at   TIMESTAMPTZ NOT NULL,

    CONSTRAINT pk_event_id PRIMARY KEY (id),
    CONSTRAINT fk_event_stadium_id FOREIGN KEY (stadium_id) REFERENCES stadium (id)
);
