CREATE TABLE IF NOT EXISTS data_tab
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    name                VARCHAR(255),
    category            VARCHAR(255),
    html_data           LONGTEXT,
    created_date        datetime(6),
    updated_date        datetime(6),
    creator_id          INT,
    styles              VARCHAR(255),
    height              INT,
    width               INT,
    CONSTRAINT data_tab_to_user_fk FOREIGN KEY (creator_id) REFERENCES user (id)
);

CREATE INDEX idx_data_tab_name ON data_tab (name);
CREATE INDEX idx_data_tab_category ON data_tab (category);

CREATE TABLE IF NOT EXISTS data_feed
(
    id                      BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_tab_id             BIGINT ,
    provider_url            VARCHAR(255),
    html_element_id         VARCHAR(255),
    CONSTRAINT data_feed_to_data_tab_fk FOREIGN KEY (data_tab_id) REFERENCES data_tab (id)
);
