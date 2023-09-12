package io.medium.poc.common.filter

import io.medium.poc.common.model.response.ApiError
import io.medium.poc.common.utils.nowLocalDateTime
import io.medium.poc.common.utils.toJson
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter

class MethodFilter: OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (request.method.equals("POST", ignoreCase = true) ||
            request.method.equals("PUT", ignoreCase = true) ||
            request.method.equals("DELETE", ignoreCase = true)
        ) {
            filterChain.doFilter(request, response)
        } else {
            sendErrorMessage(response)
        }
    }

    private fun sendErrorMessage(response: HttpServletResponse) {
        val error  = ApiError(
            code = HttpServletResponse.SC_METHOD_NOT_ALLOWED,
            message = "리퀘스트 메소드는 POST, PUT, DELETE 만 허용합니다.",
            timestamp = nowLocalDateTime(),
        )
        response.status = error.code
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(toJson(error))
    }

}
