package io.dustin.apps.common.filter

import io.dustin.apps.common.model.response.ApiError
import io.dustin.apps.common.utils.toJson
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.time.LocalDateTime.now

class MethodFilter: OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (request.method.equals("POST", ignoreCase = true) ||
            request.method.equals("PUT", ignoreCase = true) ||
            request.method.equals("DELETE", ignoreCase = true) ||
            request.method.equals("GET", ignoreCase = true) ||
            request.method.equals("PATCH", ignoreCase = true)


                ) {
            filterChain.doFilter(request, response)
        } else {
            sendErrorMessage(response)
        }
    }

    private fun sendErrorMessage(response: HttpServletResponse) {
        val error  = ApiError(
            code = HttpServletResponse.SC_METHOD_NOT_ALLOWED,
            message = "리퀘스트 메소드는 POST, PUT, DELETE, GET 만 허용합니다.",
            timestamp = now(),
        )
        response.status = error.code
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(toJson(error))
    }

}