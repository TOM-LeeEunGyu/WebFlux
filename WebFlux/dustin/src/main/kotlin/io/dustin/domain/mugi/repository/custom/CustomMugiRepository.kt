package io.dustin.domain.mugi.repository.custom

import io.dustin.domain.mugi.model.Mugi
import org.springframework.data.relational.core.sql.SqlIdentifier
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomMugiRepository {
    fun updateRecord(mugi: Mugi, assignments: MutableMap<SqlIdentifier, Any>): Mono<Record>
    fun findAllRecords(whereClause: String = "", orderClause: String = "", limitClause: String = ""): Flux<Record>


}