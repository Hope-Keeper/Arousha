package com.hotel.demo.dto;

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern


data class DocumentDto(
    @field:NotNull(message = "identifier should not be null")
    @field:Pattern(regexp = "\\d*", message = "identifier should be number")
    val identifier: String? = null,
    @NotNull
    val body: Map<String?, String?>? = null
)