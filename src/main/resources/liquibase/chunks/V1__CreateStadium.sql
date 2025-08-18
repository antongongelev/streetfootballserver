CREATE TABLE IF NOT EXISTS stadium
(
    id          SERIAL          NOT NULL,
    address     TEXT            NOT NULL,
    description TEXT            NULL,
    capacity    TEXT            NULL,
    location    GEOMETRY(Point) NOT NULL,
    avatar      TEXT            NULL,
    created_at  TIMESTAMPTZ     NOT NULL,

    CONSTRAINT pk_stadium_id PRIMARY KEY (id),
    CONSTRAINT uk_stadium_location UNIQUE (location)
);

CREATE INDEX IF NOT EXISTS stadium_location_idx ON stadium USING GIST (location);
