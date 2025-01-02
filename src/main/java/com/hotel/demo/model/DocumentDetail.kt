package com.hotel.demo.model;
import jakarta.persistence.*;

@Entity
data class DocumentDetail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    val documentDetailKey: String,
    val documentDetailValue: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="document_id")
    val document: Document

)