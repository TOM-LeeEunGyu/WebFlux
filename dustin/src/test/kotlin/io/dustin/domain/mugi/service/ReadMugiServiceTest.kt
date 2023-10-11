package io.dustin.domain.mugi.service
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import reactor.test.StepVerifier

@SpringBootTest
class ReadMugiServiceTest @Autowired constructor(
    private val read: ReadMugiService,
) {

    @Test
    @DisplayName("mugi by id test")
    fun mugiByIdTEST() {
        // given
        //val id = 2L
        val id = 1L

        // when
        val mono = read.mugiByIdOrThrow(id)

        // then
        mono.`as`(StepVerifier::create)
            .assertNext {
                assertThat(it.name).isEqualTo("Now's The Time")
            }
            .verifyComplete()
    }

    @Test
    @DisplayName("mugi by user id test")
    fun mugiByUserIdTEST() {
        // given
        //val id = 2L
        val userId = 1L

        // when
        val flux = read.mugiByUserId(userId, PageRequest.of(0, 1))
            .map { it.name }

        // then
        flux.`as`(StepVerifier::create)
            .expectNext("Now's The Time")
            .verifyComplete()
    }

    @Test
    @DisplayName("mugi Count by user Count test")
    fun mugiByUserCountTEST() {
        // given
        //val id = 2L
        val userId = 10L

        // when
        val count = read.mugiCountByUser(userId)

        // then
        count.`as`(StepVerifier::create)
            .expectNext(3)
            .verifyComplete()
    }

    @Test
    @DisplayName("mugis by converter test")
    fun mugisTEST() {
        // given

        // when
        val flux = read.mugis().map { it.user?.name }.take(1)

        // then
        flux.`as`(StepVerifier::create)
            .expectNext("dustin")
            .verifyComplete()
    }

}