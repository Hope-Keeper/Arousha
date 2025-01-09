package com.hotel.demo.service

import com.hotel.demo.dto.DocumentDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface IDocumentService {

    fun createDocument(documentDto: DocumentDto): Mono<DocumentDto>
    fun getDocuments(value: String, key: String?): Flux<DocumentDto>
}
