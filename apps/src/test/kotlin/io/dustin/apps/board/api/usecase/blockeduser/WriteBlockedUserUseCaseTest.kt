package io.dustin.apps.board.api.usecase.blockeduser

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WriteBlockedUserUseCaseTest @Autowired constructor(
    private val writeBlockedUserUseCase: WriteBlockedUserUseCase,
)
{
    @Test
    @DisplayName("writeBlockedUserUseCase API 테스트")
    fun writeBlockedUserUseCase_Test() {

    }
}
