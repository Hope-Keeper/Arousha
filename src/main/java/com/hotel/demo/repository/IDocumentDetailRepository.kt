package com.hotel.demo.repository

import com.hotel.demo.model.DocumentDetail
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface IDocumentDetailRepository: ReactiveCrudRepository<DocumentDetail, Long> {
    @Query("""
        SELECT de FROM DocumentDetail de WHERE de.document.id = :documentId
        """)
    fun getListOfDocumentDetailsByDocumentId (documentId:Long): Flux<DocumentDetail>
}


