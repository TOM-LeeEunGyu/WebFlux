package io.dustin.domain.user.service

import io.dustin.common.transaction.Transaction
import io.dustin.domain.user.model.User
import io.dustin.domain.user.model.code.Genre
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
class WriteUserServiceTest @Autowired constructor(
    private val read: ReadUserService,
    private val write: WriteUserService,
) {

    @Test
    @DisplayName("user create test")
    fun createUserTEST() {
        // given
        val createdUser = User(name = "dustin", genre = Genre.Dojuk)

        // when
        val mono = write.create(createdUser)

        // then
        mono.`as`(Transaction::withRollback)
            .`as`(StepVerifier::create)
            .assertNext {
                assertThat(it.id).isGreaterThan(0L)
            }
            .verifyComplete()
    }
}