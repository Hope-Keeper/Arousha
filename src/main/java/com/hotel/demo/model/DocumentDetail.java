package com.hotel.demo.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DocumentDetail {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
    private String documentDetailKey;
    private String documentDetailValue;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="document_id")
    private  Document document;


















}
