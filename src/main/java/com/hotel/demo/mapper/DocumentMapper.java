package com.hotel.demo.mapper;

import com.hotel.demo.dto.DocumentDto;
import com.hotel.demo.model.Document;
import com.hotel.demo.model.DocumentDetail;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DocumentMapper {



    public static DocumentDto mapToDocumentDto(Document document, List<DocumentDetail> documentDetail) {
        // Convert List<DocumentDetail> to Map<String, String>
//        Map<String, String> body = document.getBody().stream()
//                .collect(Collectors.toMap(
//                        DocumentDetail::getDocumentDetailKey,  // Map key: language (e.g., "fa", "en")
//                        DocumentDetail::getDocumentDetailValue     // Map value: content (e.g., "سلام", "hello")
//                ));
        Map<String, String> body=documentDetail.stream()
                .collect(Collectors.toMap(
                        DocumentDetail::getDocumentDetailKey,  // Map key: language (e.g., "fa", "en")
                        DocumentDetail::getDocumentDetailValue     // Map value: content (e.g., "سلام", "hello")
                ));
        // Return the DocumentDto with the identifier and the mapped body
        return new DocumentDto(
                document.getIdentifier(),
                body
        );
    }
//    public static Document mapToDocument(DocumentDto documentDto) {
//        return new Document(
//              documentDto.getIdentifier(),documentDto.getBody()
//        );
//    }


    public static Document mapToDocument(DocumentDto documentDto) {




        Document document = new Document();

        document.setIdentifier(documentDto.getIdentifier());

        // Map the body to a List<DocumentDetail>
        List<DocumentDetail> body = documentDto.getBody()
                .entrySet()
                .stream()
                .map(entry -> new DocumentDetail(null, entry.getKey(), entry.getValue(), document))
                .toList();



        for (DocumentDetail dd : body){

            documentDetailRepository.save(dd);

        }




        return document;
    }


}
