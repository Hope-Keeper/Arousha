package com.hotel.demo.model


import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class Document(
    @Id
    val id :Long?=null,
    val identifier: String?=null
)
