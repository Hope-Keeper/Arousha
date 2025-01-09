package com.hotel.demo.controller

import com.hotel.demo.dto.DocumentDto
import com.hotel.demo.service.DocumentService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api")
class DocumentController(
    private val documentService: DocumentService,
) {
      

    @PostMapping("/create")
    fun createEmployee(@RequestBody @Valid documentDto: DocumentDto?): Mono<ResponseEntity<DocumentDto>> {
        return documentService.createDocument(documentDto!!).map { documentDto1 ->
            ResponseEntity.status(HttpStatus.CREATED).body(documentDto1)
        }
    }

    @GetMapping("/all")
    fun getAllDocuments(

        @RequestParam("value") @Valid value: String, @RequestParam("key", required = false) key: String?,
    ): Flux<DocumentDto> {
        return documentService.getDocuments(value, key)
    }

}