CREATE TABLE IF NOT EXISTS data_tab
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    feed_type       VARCHAR(255),
    category        VARCHAR(255)
);

CREATE INDEX idx_feed_type ON data_tab (feed_type);
CREATE INDEX idx_category ON data_tab (category);

INSERT INTO data_tab(feed_type, category) VALUES ('UEFA_COUNTRY_COEFFICIENTS', 'FOOTBALL');
