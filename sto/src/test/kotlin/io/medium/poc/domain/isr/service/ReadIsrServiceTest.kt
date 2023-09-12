package io.medium.poc.domain.isr.service

import io.medium.poc.common.utils.toJson
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ReadIsrServiceTest @Autowired constructor(
    private val readIsrService: ReadIsrService,
) {

    @Test
    @DisplayName("retrieveIssueNo 테스트")
    fun retrieveIssueNo_TEST() {
        println(readIsrService.isrRetrieveIssueNo())
    }

    @Test
    @DisplayName("isrStoIssueApplications 테스트")
    fun isrStoIssueApplications() {

        // given
        val search = "000302987654321"

        // when
        val result = readIsrService.isrStoIssueApplications(search, 10, "")

        println(toJson(result))

        // then
        Assertions.assertThat(result.isNotEmpty()).isEqualTo(true)
    }

}
