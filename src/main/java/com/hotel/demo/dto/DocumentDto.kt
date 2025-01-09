package com.hotel.demo.dto;

import jakarta.validation.constraints.NotNull


data class DocumentDto(
    @NotNull
    val identifier: String? = null,
    @NotNull
    val body: Map<String?, String?>? = null
)