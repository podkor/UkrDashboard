CREATE TABLE IF NOT EXISTS raw_html_data
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    feed_type       VARCHAR(255),
    html_data       LONGTEXT
);

CREATE INDEX idx_feed_type
    ON raw_html_data (feed_type);

INSERT INTO raw_html_data(feed_type) VALUES ('UEFA_COUNTRY_COEFFICIENTS');
