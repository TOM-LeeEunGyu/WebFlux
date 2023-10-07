package io.dustin.api.usecase.user

import io.dustin.api.usercase.user.model.CreateUser
import io.dustin.api.usercase.user.model.UpdateUser
import io.dustin.api.usercase.user.WriteUserUseCase
import io.dustin.domain.user.model.code.Job
import io.dustin.domain.user.service.ReadUserService
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
class WriteUserUseCaseTest @Autowired constructor(
    private val writeUseCase: WriteUserUseCase,
    private val readMusicianService: ReadUserService,
) {

    @Test
    @DisplayName("user insert useCase test")
    fun insertUseCaseTEST() {
        // given
        val command = CreateUser(name = "dustin jjang jjang jjang", job = Job.Dojuk)

        // when
        val mono = writeUseCase.insert(command)
        // then
        mono.`as`(StepVerifier::create)
            .assertNext {
                assertThat(it.job).isEqualTo(Job.Dojuk)
            }
            .verifyComplete()
    }

    @Test
    @DisplayName("user update useCase test")
    fun updateUseCaseTEST() {
        // given
        val id = 4L
        val command = UpdateUser(name = "jjang jjang jjang dustin", job = null)

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