package io.dustin.apps.board.api.usecase.community.posting

import io.dustin.apps.board.domain.community.posting.model.Posting
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDto
import io.dustin.apps.board.domain.community.posting.service.WritePostingService
import io.dustin.apps.common.exception.FeignClientErrorException
import io.dustin.apps.common.external.TestCall
import io.dustin.apps.common.model.response.ResultResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WritePostingUseCase (

    private val writePostingService: WritePostingService,
    private val testCall: TestCall

) {
    @Transactional
    fun execute(userId: Long, subject: String, content: String): ResultResponse<PostingDto> {
        val posting: Posting = writePostingService.create(userId, subject, content)
        val result = PostingDto.from(posting)

        try {
            val callResponse = testCall.feignTest("테스트중!")
            println("이것이 나오면 성공한것 !")
            if(callResponse.code != HttpStatus.OK.value()) {
                throw FeignClientErrorException("데이터 전달중 장애가 발생했습니다.")
            }
        } catch (e: Exception) {
            throw FeignClientErrorException(e.message)
        }
        return ResultResponse.of(result)

    }
}
