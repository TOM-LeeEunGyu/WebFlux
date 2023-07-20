package io.dustin.apps.board.domain.blockeduser.service

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WriteBlockedUserServiceTests @Autowired constructor (
    private val writeBlockedUserService: WriteBlockedUserService,
)
{
    @Test
    @DisplayName("create API 테스트")
    fun create_Test() {
        // given
        val fromUserId = 1L
        val toUserId = 2L

        // when
        var blockUSer = writeBlockedUserService.create(fromUserId, toUserId)

        assertThat(blockUSer.toUserId).isEqualTo(2L)

    }
}