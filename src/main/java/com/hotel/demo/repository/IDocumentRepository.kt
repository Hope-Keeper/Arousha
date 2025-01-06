package com.hotel.demo.repository

import com.hotel.demo.model.Document
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface IDocumentRepository : ReactiveCrudRepository<Document, Long> {

    @Query(
        """
SELECT d.* 
FROM document_details de 
JOIN documents d ON d.id = de.document_id 
WHERE de.document_detail_value LIKE :value
"""
    )
    fun findByValue(value: String): Flux<Document>

    fun findByIdentifier(identifier: String): Mono<Document>
}
