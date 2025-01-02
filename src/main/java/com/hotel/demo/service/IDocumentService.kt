package com.hotel.demo.service

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface IDocumentService {

    fun   createDocument( documentDto: DocumentDto): Mono<DocumentDto>
    fun    getDocuments(value:String ): Flux<DocumentDto>
}

