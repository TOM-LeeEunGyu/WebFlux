package io.medium.poc.api.usecase.user

import io.medium.poc.api.controller.user.request.ComUserQuery
import io.medium.poc.common.code.YesOrNo
import io.medium.poc.common.utils.betweenLocalDate
import io.medium.poc.domain.user.model.dto.ComUserDto
import io.medium.poc.domain.user.service.ReadComUserService
import org.springframework.stereotype.Service

@Service
class ReadComUserUseCase(
    private val readComUserService: ReadComUserService,
) {

    fun execute(query: ComUserQuery): ComUserDto {

        val comUser = readComUserService.findUserById(query.userId)
        return comUser?.let {
            if(query.userPw == it.password) {
                if(betweenLocalDate(it.startUseDt, it.endUseDt)) {
                    ComUserDto(
                        userId = it.id,
                        userName = it.userName,
                        userAuth = it.userAuth,
                        validationYN = YesOrNo.Y.name,
                        resultMsg = "로그인이 성공했습니다.",
                    )
                } else {
                    ComUserDto(
                        validationYN = YesOrNo.N.name,
                        resultMsg = "사용기간이 종료되었습니다.",
                    )
                }
            } else {
                ComUserDto(
                    validationYN = YesOrNo.N.name,
                    resultMsg = "사용자 비밀번호가 맞지 않습니다. 비밀번호를 확인하세요.",
                )
            }
        } ?: ComUserDto(validationYN = YesOrNo.N.name, resultMsg = "사용자 아이디가 존재하지 않습니다. 아이디를 확인하세요.")
    }

}
