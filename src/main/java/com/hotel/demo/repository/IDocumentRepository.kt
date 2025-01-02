import com.hotel.demo.model.Document
import com.hotel.demo.model.DocumentDetail
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface IDocumentRepository : ReactiveCrudRepository<Document, Long> {

    @Query(
        """
        SELECT d FROM DocumentDetail de 
        JOIN de.document d 
        WHERE de.documentDetailValue LIKE %:value%
        """
    )
    fun findByValue(value: String): Flux<Document>

    fun findByIdentifier(identifier: String): Mono<Document>
}
