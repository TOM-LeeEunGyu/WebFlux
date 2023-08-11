package io.dustin.apps.board.api.usecase.community.posting

import io.dustin.apps.board.domain.community.posting.model.Posting
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDto
import io.dustin.apps.board.domain.community.posting.service.WritePostingService
import io.dustin.apps.common.exception.FeignClientErrorException
import io.dustin.apps.common.external.TestCall
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WritePostingUseCase (

    private val writePostingService: WritePostingService,
    private val testCall: TestCall

) {
    @Transactional
    fun execute(userId: Long, subject: String, content: String): PostingDto {
        val posting: Posting = writePostingService.create(userId, subject, content)
        return PostingDto.from(posting)


        try {
            val callResponse = testCall.feignTest("테스트중!")
            if(callResponse.code != HttpStatus.OK.value()) {
                throw FeignClientErrorException("데이터 전달중 장애가 발생했습니다.")
            }
        } catch (e: Exception) {
            throw FeignClientErrorException(e.message)
        }

    }
}
