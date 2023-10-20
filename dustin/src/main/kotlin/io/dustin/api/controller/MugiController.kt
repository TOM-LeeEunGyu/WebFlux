package io.dustin.api.controller

import io.dustin.api.usercase.mugi.ReadMugiUseCase
import io.dustin.api.usercase.mugi.WriteMugiUseCase
import io.dustin.api.usercase.mugi.model.CreateMugi
import io.dustin.api.usercase.mugi.model.UpdateMugi
import io.dustin.common.model.request.QueryPage
import io.dustin.domain.mugi.model.Mugi
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.util.MultiValueMap
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Validated
@RestController
@RequestMapping("/api/v1/mugis")
class MugiController(
    private val readMugiUseCase: ReadMugiUseCase,
    private val writeMugiUseCase: WriteMugiUseCase,
) {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun fetchMugi(@PathVariable("id") id: Long): Mono<Mugi> {
        return readMugiUseCase.mugiById(id)
    }

    @GetMapping("/query/{queryCondition}")
    @ResponseStatus(HttpStatus.OK)
    fun fetchMugi(
        @Valid queryPage: QueryPage,
        @MatrixVariable(pathVar = "queryCondition", required = false) matrixVariable: MultiValueMap<String, Any>
    ): Flux<Mugi> {
        return readMugiUseCase.allMugis(queryPage, matrixVariable)
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    fun fetchMugiByUser(@Valid queryPage: QueryPage, @PathVariable("userId") userId: Long): Mono<Page<Mugi>> {
        return readMugiUseCase.recordByMusicianId(queryPage, userId)
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun createMugi(@RequestBody @Valid command: CreateMugi): Mono<Mugi> {
        return writeMugiUseCase.insert(command)
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateMugi(@PathVariable("id") id: Long, @RequestBody command: UpdateMugi): Mono<Mugi> {
        return writeMugiUseCase.update(id, command)
    }

}