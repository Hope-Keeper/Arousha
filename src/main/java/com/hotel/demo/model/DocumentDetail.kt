package com.hotel.demo.model;
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class DocumentDetail(

    @Id
    val id: Long?=null,

    val documentDetailKey: String?=null,
    val documentDetailValue: String?=null,
    val documentId:Long?=null,

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="document_id")
//    val document: Document

)