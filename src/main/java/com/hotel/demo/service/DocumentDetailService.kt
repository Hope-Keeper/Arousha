package com.hotel.demo.service;

import com.hotel.demo.repository.IDocumentRepository
//import com.hotel.demo.dto.DocumentDto
//import com.hotel.demo.mapper.DocumentMapper;
//import com.hotel.demo.repository.IDocumentDetailRepository
//import org.springframework.stereotype.Service
//import reactor.core.publisher.Mono
//import reactor.kotlin.core.publisher.toMono
//
//
//@Service
//class DocumentDetailService(
//    private val documentRepository: IDocumentRepository,
//    private val documentDetailRepository: IDocumentDetailRepository
//) : IDocumentDetailService {
//
//     fun createDocumentDetail(documentDto: DocumentDto): Mono<DocumentDto> {
//         val document = DocumentMapper.mapToDocument(documentDto);
//         //val savedDocument = documentRepository.save(document);
//
//val ds=documentDetailRepository.saveAll(documentDto.body)
//         return documentRepository.save(document).map   { document->
//
//
//             DocumentMapper.mapToDocumentDto(document)}
//
//
//     }
//
//
//
//     }
//
//}