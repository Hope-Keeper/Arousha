package com.hotel.demo.controller

import com.hotel.demo.dto.DocumentDto
import com.hotel.demo.service.DocumentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Controller
class DocumentController(
    private val documentService: DocumentService,
) {


    @PostMapping("/create")
    fun createEmployee(@RequestBody documentDto: DocumentDto?): Mono<ResponseEntity<DocumentDto>> {
        return documentService.createDocument(documentDto!!).map { documentDto1 ->
            ResponseEntity.status(HttpStatus.CREATED).body(documentDto1)
        }
    }

    @GetMapping("/all")
    fun getAllDocuments(@RequestParam("value") value: String?): Flux<DocumentDto> {
        return documentService.getDocuments(value!!)
    }

}