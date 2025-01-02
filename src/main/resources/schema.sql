CREATE TABLE document (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    identifier VARCHAR(255) NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE document_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    document_detail_key VARCHAR(255) NOT NULL,
       document_detail_value VARCHAR(255) NOT NULL,
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    document_id BIGINT,
        CONSTRAINT FK_document FOREIGN KEY (document_id) REFERENCES document(id)
);