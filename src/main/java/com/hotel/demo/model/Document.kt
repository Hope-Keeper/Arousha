package com.hotel.demo.model


import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("documents")
data class Document(
    @Id
    var id: Long? = null,
    val identifier: String
)
