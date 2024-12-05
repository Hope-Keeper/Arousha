package com.hotel.demo.service;

import com.hotel.demo.dto.DocumentDto;
import com.hotel.demo.mapper.DocumentMapper;
import com.hotel.demo.model.Document;
import com.hotel.demo.repository.IDocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DocumentService implements IDocumentService{
    private final IDocumentRepository documentRepository;




    @Override
    public DocumentDto createDocument(DocumentDto documentDto) {
        Document document= DocumentMapper.mapToDocument(documentDto);
        Document savedDocument=  documentRepository.save(document);


        return DocumentMapper.mapToDocumentDto(savedDocument);
    }

    @Override
    public List<DocumentDto> getDocuments(String val) {

       List<Document> docs= documentRepository.findByValue(val);

        return docs.stream().map((emp)->DocumentMapper.mapToDocumentDto(emp)).collect(Collectors.toList());

    }

}