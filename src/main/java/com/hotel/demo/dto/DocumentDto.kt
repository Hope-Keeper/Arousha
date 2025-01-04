package com.hotel.demo.dto;

import org.jetbrains.annotations.NotNull

data class DocumentDto(
    @NotNull
    val identifier: String?=null,
    @NotNull
    val body: Map<String?, String? >?=null
)