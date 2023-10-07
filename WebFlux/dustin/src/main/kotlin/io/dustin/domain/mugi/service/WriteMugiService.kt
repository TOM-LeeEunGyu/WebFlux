package io.dustin.domain.mugi.service

import io.dustin.domain.mugi.model.Mugi
import io.dustin.domain.mugi.repository.MugiRepository
import org.springframework.data.relational.core.sql.SqlIdentifier
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class WriteMugiService(
    private val mugiRepository: MugiRepository,
) {
    fun create(mugi: Mugi): Mono<Mugi> {
        return mugiRepository.save(mugi)
    }

    fun update(mugi: Mugi, assignments: MutableMap<SqlIdentifier, Any>): Mono<Mugi> {
        return mugiRepository.updateMugi(mugi, assignments)
    }
}