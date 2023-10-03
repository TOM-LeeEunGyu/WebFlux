package io.dustin.api.controller

import io.dustin.api.model.CreateUser
import io.dustin.api.model.UpdateUser
import io.dustin.api.usercase.user.ReadUserUseCase
import io.dustin.api.usercase.user.WriteUserUseCase
import io.dustin.common.model.request.QueryPage
import io.dustin.domain.user.model.User
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.util.MultiValueMap
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@Validated
@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val readUserUseCase: ReadUserUseCase,
    private val writeUserUseCase: WriteUserUseCase,
) {

    @GetMapping("/query/{queryCondition}")
    fun fetchUsers(@Valid queryPage: QueryPage,
                       @MatrixVariable(pathVar = "queryCondition", required = false) matrixVariable: MultiValueMap<String, Any>): Mono<Page<User>> {
        return readUserUseCase.usersByQuery(queryPage, matrixVariable)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun fetchUsers(@PathVariable("id") id: Long): Mono<User> {
        return readUserUseCase.userById(id)
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody @Valid command: CreateUser): Mono<User> {
        return writeUserUseCase.insert(command)
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateUser(@PathVariable("id") id: Long, @RequestBody command: UpdateUser): Mono<User> {
        return writeUserUseCase.update(id, command)
    }

}