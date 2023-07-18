package io.dustin.apps.common.validation

import jakarta.validation.constraints.Email

@Getter
@Setter
class UserCreateForm {
    @Size(min = 3, max = 25)
    @NotEmpty(message = "사용자ID는 필수항목입니다.")
    private val username: String? = null

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private val password1: String? = null

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private val password2: String? = null

    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email
    private val email: String? = null
}
