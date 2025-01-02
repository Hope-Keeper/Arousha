package com.hotel.demo.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.sql.Blob
import java.util.*

@Entity
data class Document(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    val identifier: String
)
