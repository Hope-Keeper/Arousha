package com.hotel.demo.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("document_details")
data class DocumentDetail(

    @Id
    val id: Long? = null,

    val documentDetailKey: String,
    val documentDetailValue: String,
    val documentId: Long,

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="document_id")
//    val document: Document

)