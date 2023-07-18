package io.dustin.apps.board.domain.blockeduser.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ReadBlockedUserServiceTests @Autowired constructor(
	private val readBlockedUserService: ReadBlockedUserService,
) {

	@Test
	@DisplayName("getBlockedUser API 테스트")
	fun getBlockedUser_TEST() {

		// given
		val fromUserId = 1L
		val toUserId = 2L

		//  when
		val blockedUser = readBlockedUserService.getBlockedUser(fromUserId, toUserId)

		assertThat(blockedUser.id).isEqualTo(2L)

	}

}
