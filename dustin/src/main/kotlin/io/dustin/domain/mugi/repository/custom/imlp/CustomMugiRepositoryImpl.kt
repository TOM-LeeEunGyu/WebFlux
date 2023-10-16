package io.dustin.domain.mugi.repository.custom.imlp

import io.dustin.domain.mugi.mapper.MugiMapper
import io.dustin.domain.mugi.model.Mugi
import io.dustin.domain.mugi.repository.custom.CustomMugiRepository
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.Query.query
import org.springframework.data.relational.core.query.Update
import org.springframework.data.relational.core.sql.SqlIdentifier
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class CustomMugiRepositoryImpl(
    private val query: R2dbcEntityTemplate,
    private val mugiMapper: MugiMapper,
): CustomMugiRepository {

    override fun updateMugi(mugi: Mugi, assignments: MutableMap<SqlIdentifier, Any>): Mono<Mugi> {
        return query.update(Mugi::class.java)
            .matching(query(where("id").`is`(mugi.id!!)))
            .apply(Update.from(assignments))
            .thenReturn(mugi)
    }

    override fun findAllMugis(whereClause: String, orderClause: String, limitClause: String): Flux<Mugi> {
        var sql = """
            SELECT user.name AS userName,
                   user.genre,
                   user.created_at AS mCreatedAt,
                   user.updated_at AS mUpdatedAt,
                   mugi.*
              FROM mugi
              INNER JOIN user
              ON mugi.user_id = user.id
              WHERE 1 = 1 
              $whereClause
              $orderClause
              $limitClause
        """.trimIndent()
        return query.databaseClient
            .sql(sql)
            .map(mugiMapper::apply)
            .all()
    }


}