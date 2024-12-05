package com.hotel.demo.repository;

import com.hotel.demo.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDocumentRepository extends JpaRepository<Document,Long> {

    @Query("SELECT d FROM DocumentDetail de JOIN de.document d WHERE de.documentDetailValue LIKE %:value%")
    List<Document> findByValue(@Param("value") String value);


}
