package com.hotel.demo.controller;

import com.hotel.demo.dto.DocumentDto;
import com.hotel.demo.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
@AllArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping
    public ResponseEntity<DocumentDto> createEmployee(@RequestBody DocumentDto documentDto) {
        DocumentDto savedDocumentDto=  documentService.createDocument(documentDto);
        return new ResponseEntity<>(savedDocumentDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DocumentDto>> getAllDocuments(@RequestParam("value") String value) {
        List<DocumentDto> documentDtos = documentService.getDocuments(value);
        return ResponseEntity.ok(documentDtos);
    }




}
