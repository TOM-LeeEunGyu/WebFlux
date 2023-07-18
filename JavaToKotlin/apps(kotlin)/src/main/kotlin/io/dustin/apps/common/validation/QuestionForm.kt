package io.dustin.apps.common.validation

import jakarta.validation.constraints.NotEmpty

@Getter
@Setter
class QuestionForm {
    @NotEmpty(message = "제목은 필수항목입니다.") // message 속성은 검증이 실패할 경우 표시할 오류 메세지 이다.
    @Size(max = 200)
    private val subject: String? = null

    @NotEmpty(message = "내용은 필수항목입니다.")
    private val content: String? = null
}
