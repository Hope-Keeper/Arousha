package com.hotel.demo.controller;

import com.hotel.demo.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/documents")
@AllArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping
    Mono<ResponseEntity<DocumentDto> > createEmployee(@RequestBody DocumentDto documentDto) {

        return documentService.createDocument(documentDto).map(documentDto1 ->ResponseEntity.status(HttpStatus.CREATED).body(documentDto1) );





    }

    @GetMapping
    Flux<DocumentDto> getAllDocuments(@RequestParam("value") String value) {

        return documentService.getDocuments(value);
    }




}
