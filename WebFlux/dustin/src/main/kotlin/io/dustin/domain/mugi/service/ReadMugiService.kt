package io.dustin.domain.mugi.service

import io.dustin.common.extensions.findByIdOrThrow
import io.dustin.domain.mugi.repository.MugiRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ReadMugiService(
    private val mugiRepository: MugiRepository,
) {

    fun mugiById(id: Long) = mugiRepository.findById(id)
    fun mugiByIdOrThrow(id: Long, message: String? = null) = mugiRepository.findByIdOrThrow(id, message)
    fun mugiByMusicianId(musicianId: Long, pageable: Pageable) = mugiRepository.findByMusicianId(musicianId, pageable)
    fun mugiCountByMusician(musicianId: Long) = mugiRepository.countByMusicianId(musicianId)
    fun allRecords(whereClause: String = "",
                   orderClause: String = "",
                   limitClause: String = "") = mugiRepository.findAllRecords(whereClause, orderClause, limitClause)
    fun records() = mugiRepository.findRecords()
}