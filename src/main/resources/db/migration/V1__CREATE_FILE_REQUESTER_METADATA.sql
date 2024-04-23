CREATE TABLE if not exists file_metadata (
    id varchar,
    request_id varchar,
    request_uri varchar,
    request_timestamp timestamp,
    ip_address varchar,
    country_code varchar,
    isp varchar,
    ip_validation_response_code varchar,
    response_timestamp timestamp,
    PRIMARY KEY (id)
);