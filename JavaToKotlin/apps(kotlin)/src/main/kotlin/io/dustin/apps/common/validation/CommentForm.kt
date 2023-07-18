package io.dustin.apps.common.validation

import jakarta.validation.constraints.NotEmpty

@Getter
@Setter
class CommentForm {
    @NotEmpty(message = "내용은 필수항목입니다.")
    private val content: String? = null
}