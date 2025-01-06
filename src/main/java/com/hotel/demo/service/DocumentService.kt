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


@Service
class DocumentService(
    private val documentRepository: IDocumentRepository,
    private val documentDetailRepository: IDocumentDetailRepository
) : IDocumentService {

    override fun createDocument(documentDto: DocumentDto): Mono<DocumentDto> {
        val listOfKeys = documentDto.body?.keys?.toList()
        val listOfKeyValues = documentDto.body?.entries?.toList()
        println("111111111")
        print(listOfKeyValues)
        val newDocument = Document(null, identifier = documentDto.identifier!!)
        val tobedeletedList = ArrayList<Map.Entry<String?, String?>>()
        val tobeUpdatedList = ArrayList<Map.Entry<String?, String?>>()
        val tobeInsertedList = ArrayList<Map.Entry<String?, String?>>()

        val existsDoc = documentRepository.findByIdentifier(documentDto.identifier!!)
            .flatMap { document ->
                newDocument.id = document.id
                documentDetailRepository.getListOfDocumentDetailsByDocumentId(document.id!!)
                    .map { detail ->

                        val tbi = listOfKeyValues?.mapNotNull { keyValue ->
                            if (keyValue.key != detail.documentDetailKey && keyValue.value != detail.documentDetailValue) {
                                tobeInsertedList.add(keyValue)
                                return@map keyValue
                            } else if (keyValue.key == detail.documentDetailKey && keyValue.value != detail.documentDetailValue) {
                                tobeUpdatedList.add(keyValue)
                                null
                            } else {

                                tobedeletedList.add(mapOf(detail.documentDetailKey to detail.documentDetailValue))
                                null
                            }
                        }


                    }
                    .collectList()

            }//bodyDetailsToInserted=documentDto...



        return documentRepository.save(newDocument).flatMap { savedDocument ->
//            val bodyDetails = documentDto.body?.entries?.map { (key, value) ->
//                DocumentDetail(null, key!!, value!!, savedDocument.id!!)
//
//            }
            val bodyDetailsToInserted = tobeInsertedList.map { t ->
                DocumentDetail(null, t.key!!, t.value!!, savedDocument.id!!)
            }

            val bodyDetailstobedeletedList = tobedeletedList.map { t ->
                DocumentDetail(null, t.key!!, t.value!!, savedDocument.id!!)

            }


            val bodyDetailstobeUpdatedList = tobeUpdatedList.map { t ->
                DocumentDetail(null, t.key!!, t.value!!, savedDocument.id!!)

            }


            val savedDetails = documentDetailRepository.saveAll(bodyDetailsToInserted).collectList()

            val deletedDetails = documentDetailRepository.deleteAll(bodyDetailstobedeletedList)

            val updatedDetails = documentDetailRepository.deleteAll(bodyDetailstobeUpdatedList)



            Mono.zip(savedDetails, deletedDetails, updatedDetails)
                .map { t ->
                    DocumentMapper.mapToDocumentDto(savedDocument, t.t1 + t.t3)
                }
        }

    }

    override fun getDocuments(value: String): Flux<DocumentDto> {
        return documentRepository.findByValue(value)
            .flatMap { document ->
                documentDetailRepository.getListOfDocumentDetailsByDocumentId(document.id!!)
                    .collectList()
                    .map { details -> DocumentMapper.mapToDocumentDto(document, details) }
            }
    }
}