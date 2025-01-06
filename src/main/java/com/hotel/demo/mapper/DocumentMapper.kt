package com.hotel.demo.mapper

import com.hotel.demo.dto.DocumentDto
import com.hotel.demo.model.Document
import com.hotel.demo.model.DocumentDetail

object DocumentMapper {


    fun mapToDocumentDto(document: Document, documentDetail: List<DocumentDetail>): DocumentDto =
        DocumentDto(
            document.identifier!!,
            documentDetail.associate { Pair(it.documentDetailKey, it.documentDetailValue) }
        )

    //    public static Document mapToDocument(DocumentDto documentDto) {
    //        return new Document(
    //              documentDto.getIdentifier(),documentDto.getBody()
    //        );
    //    }
    fun mapToDocument(documentDto: DocumentDto): Document = Document(null, documentDto.identifier!!)


}
