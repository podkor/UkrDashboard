CREATE TABLE IF NOT EXISTS country_coefficients
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    season              VARCHAR(255),
    position            INT,
    country             VARCHAR(255),
    season_1_points     DECIMAL(5, 3),
    season_2_points     DECIMAL(5, 3),
    season_3_points     DECIMAL(5, 3),
    season_4_points     DECIMAL(5, 3),
    season_5_points     DECIMAL(5, 3),
    total_points        DECIMAL(5, 3),
    remained_clubs      INT,
    total_clubs         INT,
    flag_url            BIGINT
);

CREATE INDEX idx_season
    ON country_coefficients (season);
