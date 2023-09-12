package io.medium.poc.api.controller.user

import io.medium.poc.api.controller.sec.request.query.*
import io.medium.poc.api.controller.user.request.ComUserQuery
import io.medium.poc.api.usecase.sec.*
import io.medium.poc.api.usecase.user.ReadComUserUseCase
import io.medium.poc.domain.sec.model.dto.*
import io.medium.poc.domain.user.model.dto.ComUserDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * com user read 컨트롤러
 */
@RestController
@RequestMapping("/api/poc/user")
@Tag(name = "공통 사용자 API", description = "공통 사용자 API 를 제공한다.")
class ReadComUserController(
    private val readComUserUseCase: ReadComUserUseCase,
) {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    @Operation(
        summary = "로그인 API",
        description = "로그인을 수행한다."
    )
    fun login(@RequestBody @Valid query: ComUserQuery): ComUserDto {
        return readComUserUseCase.execute(query)
    }

}
