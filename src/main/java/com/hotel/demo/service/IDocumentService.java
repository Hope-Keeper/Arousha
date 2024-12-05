package com.hotel.demo.service;

import com.hotel.demo.dto.DocumentDto;
import com.hotel.demo.model.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface IDocumentService {

    DocumentDto createDocument(DocumentDto documentDto);

List<DocumentDto> getDocuments(String val);
}
