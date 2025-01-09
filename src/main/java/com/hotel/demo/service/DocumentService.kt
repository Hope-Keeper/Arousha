package com.hotel.demo.service;

import com.hotel.demo.dto.DocumentDto
import com.hotel.demo.mapper.DocumentMapper
import com.hotel.demo.model.Document
import com.hotel.demo.model.DocumentDetail
import com.hotel.demo.repository.IDocumentDetailRepository
import com.hotel.demo.repository.IDocumentRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty


@Service
class DocumentService(
    private val documentRepository: IDocumentRepository,
    private val documentDetailRepository: IDocumentDetailRepository
) : IDocumentService {

    override fun createDocument(documentDto: DocumentDto): Mono<DocumentDto> {
        val listOfKeyValues = documentDto.body?.entries?.toList()?.filter {
            it.key != null && it.value != null
        } ?: return Mono.empty()
//        println("111111111")
//        print(listOfKeyValues)
        val newDocument = Document(null, identifier = documentDto.identifier!!)
//
//
//        val existsDoc = documentRepository.findByIdentifier(documentDto.identifier!!)
//            .flatMap { document ->
//                newDocument.id = document.id
//                documentDetailRepository.getListOfDocumentDetailsByDocumentId(document.id!!)
//                    .map { detail ->
//
//                        val tbi = listOfKeyValues?.mapNotNull { keyValue ->
//                            if (keyValue.key != detail.documentDetailKey && keyValue.value != detail.documentDetailValue) {
//                                tobeInsertedList.add(keyValue)
//                                return@map keyValue
//                            } else if (keyValue.key == detail.documentDetailKey && keyValue.value != detail.documentDetailValue) {
//                                tobeUpdatedList.add(keyValue)
//                                null
//                            } else {
//
//                                tobedeletedList.add(mapOf(detail.documentDetailKey to detail.documentDetailValue))
//                                null
//                            }
//                        }
//
//
//                    }
//                    .collectList()
//
//            }//bodyDetailsToInserted=documentDto...


        return createOrUpdate(documentDto).flatMap { savedDocument ->
            documentDetailRepository.getListOfDocumentDetailsByDocumentId(savedDocument.id!!)
                .collectList().defaultIfEmpty(emptyList())
                .map { existingDetails ->
                    val toBeInserted = mutableListOf<DocumentDetail>()
                    val toBeDeleted = mutableListOf<DocumentDetail>()
                    listOfKeyValues.forEach { keyValue ->
                        val existingDetail = existingDetails.find { it.documentDetailKey == keyValue.key }
                        if (existingDetail == null) {
                            toBeInserted.add(
                                DocumentDetail(
                                    existingDetail?.id,
                                    keyValue.key!!,
                                    keyValue.value!!,
                                    savedDocument.id!!
                                )
                            )
                        } else {
                            if (existingDetail.documentDetailValue !== keyValue.value) {

                                toBeInserted.add(
                                    DocumentDetail(
                                        existingDetail.id,
                                        keyValue.key!!,
                                        keyValue.value!!,
                                        savedDocument.id!!
                                    )
                                )

                            }
                            existingDetails.remove(existingDetail)


                        }


                    }
                    existingDetails.forEach { e -> toBeDeleted.add(e) }
                    Pair(toBeInserted, toBeDeleted)
                }
                .flatMap { (toBeInserted, toBeDeleted) ->
                    val savedDetails = documentDetailRepository.saveAll(toBeInserted).collectList()

                    val deletedDetails = documentDetailRepository.deleteAll(toBeDeleted).then(Mono.just(true))


                    Mono.zip(savedDetails, deletedDetails)
                        .map { t ->
                            DocumentMapper.mapToDocumentDto(savedDocument, t.t1)
                        }

                }
        }

        ////////////////////////////////////////////

    }


    private fun createOrUpdate(documentDto: DocumentDto): Mono<Document> =
        documentRepository.findByIdentifier(documentDto.identifier!!)
            .switchIfEmpty {
                documentRepository.save(Document(identifier = documentDto.identifier))
            }


    override fun getDocuments(value: String, key: String?): Flux<DocumentDto> {
        return documentRepository.findByValue(value, key)
            .flatMap { document ->
                documentDetailRepository.getListOfDocumentDetailsByDocumentId(document.id!!)
                    .collectList()
                    .map { details -> DocumentMapper.mapToDocumentDto(document, details) }
            }
    }
}