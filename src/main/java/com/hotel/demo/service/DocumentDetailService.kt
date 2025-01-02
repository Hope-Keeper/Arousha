package com.hotel.demo.service;

import com.hotel.demo.dto.DocumentDto
import com.hotel.demo.mapper.DocumentMapper;
import com.hotel.demo.model.Document;
import com.hotel.demo.model.DocumentDetail
import com.hotel.demo.repository.IDocumentDetailRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

public class DocumentDetailService {




    public DocumentDto createDocumentDetail(DocumentDto documentDto) {
        Document document= DocumentMapper.mapToDocument(documentDto);
        Document savedDocument=  documentRepository.save(document);


        return DocumentMapper.mapToDocumentDto(savedDocument);
    }

}
@Service
class DocumentDetailService(
    private val documentRepository: IDocumentRepository,
    private val documentDetailRepository: IDocumentDetailRepository
) : IDocumentService {

    override fun createDocumentDetail(documentDto: DocumentDto): Mono<DocumentDto> {
        val listOfKeys = documentDto.body.keys.toList()

        val existsDoc = documentRepository.findByIdentifier(documentDto.identifier)
        val toBeDeleted = existsDoc.flatMap { document ->
            documentDetailRepository.getListOfDocumentDetailsByDocumentId(document.id)
                .filter { detail -> !listOfKeys.contains(detail.documentDetailKey) }
                .collectList()
        }

        val newDocument = Document(identifier = documentDto.identifier)
        val savedDocument = documentRepository.save(newDocument)

        val bodyDetails = documentDto.body.entries.mapNotNull { (key, value) ->
            if (listOfKeys.contains(key)) DocumentDetail(null, key, value, newDocument) else null
        }

        val savedDetails = documentDetailRepository.saveAll(bodyDetails).collectList()

        return Mono.zip(savedDocument, savedDetails, toBeDeleted)
            .map { t ->
                DocumentMapper.mapToDocumentDto(t.t1,t.t2)
            }
    }

    override fun getDocuments(value: String): Flux<DocumentDto> {
        return documentRepository.findByValue(value)
            .flatMap { document ->
                documentDetailRepository.getListOfDocumentDetailsByDocumentId(document.id)
                    .collectList()
                    .map { details -> DocumentMapper.mapToDocumentDto(document, details) }
            }
    }
}