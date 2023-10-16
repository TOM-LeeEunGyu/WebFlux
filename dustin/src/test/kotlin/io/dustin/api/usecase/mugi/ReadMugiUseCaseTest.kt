package io.dustin.api.usecase.mugi

import io.dustin.api.usercase.mugi.ReadMugiUseCase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

@SpringBootTest
class ReadMugiUseCaseTest @Autowired constructor(
    private val readUseCase: ReadMugiUseCase,
) {

    @Test
    @DisplayName("mugi by id test")
    fun userByIdTEST() {
        // given
        val id = 1L

        // when
        val mono = readUseCase.mugiById(id)

        // then
        mono.`as`(StepVerifier::create)
            .assertNext {
                assertThat(it.name).isEqualTo("Bird At St. Nick's")
            }
            .verifyComplete()
    }
}