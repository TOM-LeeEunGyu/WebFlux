package io.dustin.api.controller

import io.dustin.api.model.CreateUser
import io.dustin.api.model.UpdateUser
import io.dustin.domain.user.model.code.Job
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

@SpringBootTest
@AutoConfigureWebTestClient
class UserControllerTest @Autowired constructor(
    private val webTestClient: WebTestClient,
) {

    @Test
    @DisplayName("user create test")
    fun createMusicianTEST() {
        // given
        val createUser = CreateUser(name = "dustin", job = Job.Dojuk)

        /**
         * 비동기적으로 요청하는 non-blocking 처리 방식
         * 요청을 보내고 응답을 받을 때까지 대기하지 않기 때문에 처리 속도가 빠름
         * 비동기 처리 방식으로 인해 대용량 처리를 할 때 용이함
         * WebTestClient를 주입받기 위해서는 위 코드에서 볼 수 있듯이 @AutoConfigureWebTestClient설정을 해주면 된다.
         */
        // when
        webTestClient.post()
            .uri("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(createUser), CreateUser::class.java::class.java)
            .exchange()
            .expectStatus().isCreated
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            // then
            .jsonPath("$.name").isEqualTo("dustin")
    }

    @Test
    @DisplayName("user update test")
    fun updateUserTEST() {
        // given
        val update = UpdateUser(name = null, job = Job.Junsa)

        // when
        webTestClient.patch()
            .uri("/api/v1/users/5")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(update), UpdateUser::class.java::class.java)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            // then
            .jsonPath("$.job").isEqualTo("Junsa")
    }

    @Test
    @DisplayName("fetchUser test")
    fun fetchUserTEST() {
        // given
        val id = 5L

        // when
        webTestClient.get()
            .uri("/api/v1/users/$id")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            // then
            .jsonPath("$.name").isEqualTo("dustin")

    }


    @Test
    @DisplayName("fetchUsers adjust Matrix Variable test")
    fun fetchUsersTEST() {

        // given
        val page = 1
        val size = 10

        // when
        webTestClient.get()
            .uri("/api/v1/users/query/search;name=like,dustin?page=$page&size=$size")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            // then
            .jsonPath("$.content[0].name").isEqualTo("dustin hwang ")

    }

}