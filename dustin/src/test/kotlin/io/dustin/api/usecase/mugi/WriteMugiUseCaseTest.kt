package io.dustin.api.usecase.mugi

import io.dustin.api.usercase.mugi.WriteMugiUseCase
import io.dustin.api.router.mugi.model.CreateMugi
import io.dustin.api.router.mugi.model.UpdateMugi
import io.dustin.domain.mugi.model.code.MugiFormat
import io.dustin.domain.mugi.model.code.ReleasedType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.transaction.TransactionalTestExecutionListener
import reactor.test.StepVerifier

@SpringBootTest
@TestExecutionListeners(
    listeners = [TransactionalTestExecutionListener::class],
    mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
class WriteMugiUseCaseTest @Autowired constructor(
    private val writeUseCase: WriteMugiUseCase,
) {

    @Test
    @DisplayName("mugi insert useCase test")
    fun insertUseCaseTEST() {
        // given
        val formats = listOf(MugiFormat.GUM, MugiFormat.ADAE).joinToString(separator = ",") { it.name }
        val createdMugi = CreateMugi(
            userId = 1,
            name = "Bird At St Nicks",
            label = "Fantasy",
            format = formats,
            releasedType = ReleasedType.JAK,
            releasedYear = 1957,
        )
        // when
        val mono = writeUseCase.insert(createdMugi)
        // then
        mono.`as`(StepVerifier::create)
            .assertNext {
                assertThat(it.name).isEqualTo("Bird At St Nicks")
            }
            .verifyComplete()
    }

    @Test
    @DisplayName("mugi update useCase test")
    fun updateUseCaseTEST() {
        // given
        val id = 1L
        val command = UpdateMugi(
            name = "Bird At St. Nick's",
            label = null,
            releasedType = null,
            releasedYear = null,
            format = null,
        )

        // when
        val mono = writeUseCase.update(id, command)

        // then
        mono.`as`(StepVerifier::create)
            .assertNext {
                assertThat(it.name).isEqualTo(command.name)
            }
            .verifyComplete()
    }

}