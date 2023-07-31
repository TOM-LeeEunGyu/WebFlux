package io.dustin.apps.common.utils

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

/**
 * kotlin jackson object mapper
 */
val mapper = jacksonObjectMapper().also {
    it.registerModule(JavaTimeModule())
    it.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
}

/**
 * 객체를 받아서 json 스트링으로 반환한다.
 *
 * @param any
 * @return String
 */
fun toJson(any: Any): String = mapper.writeValueAsString(any)

/**
 * json 스트링을 해당 객체로 매핑해서 반환한다.
 *
 * @param json
 * @param valueType
 * @return T
 */
fun <T> toObject(json: String, valueType: Class<T>): T = mapper.readValue(json, valueType)
