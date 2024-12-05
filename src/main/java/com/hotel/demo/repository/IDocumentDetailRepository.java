package com.hotel.demo.repository;

import com.hotel.demo.model.Document;
import com.hotel.demo.model.DocumentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDocumentDetailRepository extends JpaRepository<DocumentDetail,Long> {



}

