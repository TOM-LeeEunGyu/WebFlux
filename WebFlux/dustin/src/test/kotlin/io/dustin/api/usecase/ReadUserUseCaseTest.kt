package io.dustin.api.usecase

import io.dustin.api.usercase.user.ReadUserUseCase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.test.StepVerifier

@SpringBootTest
class ReadUserUseCaseTest @Autowired constructor(
    private val readUseCase: ReadUserUseCase,
) {

    @Test
    @DisplayName("userById test")
    fun userByIdTEST() {
        // given
        val id = 4L

        // when
        val mono = readUseCase.userById(id)

        // then
        mono.`as`(StepVerifier::create)
            .assertNext {
                assertThat(it.name).isEqualTo("jjang jjang jjang dustin")
            }
            .verifyComplete()
    }

}