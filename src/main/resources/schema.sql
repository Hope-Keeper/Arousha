CREATE TABLE document
(
    id         BIGSERIAL    NOT NULL PRIMARY KEY,
    identifier VARCHAR(255) NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE document_details
(
    id                    BIGSERIAL PRIMARY KEY,
    document_detail_key   VARCHAR(255) NOT NULL,
    document_detail_value VARCHAR(255) NOT NULL,
    created_at            TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    document_id           BIGINT       NOT NULL,
    CONSTRAINT FK_document FOREIGN KEY (document_id) REFERENCES document (id)
);