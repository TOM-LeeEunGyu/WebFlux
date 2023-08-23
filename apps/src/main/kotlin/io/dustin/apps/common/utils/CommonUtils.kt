package io.dustin.apps.common.utils

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.dustin.apps.common.provider.ApplicationContextProvider

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

/**
 * 컬렉션의 마지막 요소를 가져온다.
 */
fun <T> List<T>.lastAt(): T {
    return this[this.size - 1]
}

/** inline은 반복적으로 호출할 때 오버헤드를 줄일 수 있다고 한다 */
inline fun <reified T> getBean(beanId: String, clazz: Class<T>): T {
    val applicationContext = ApplicationContextProvider.applicationContext
    return applicationContext.getBean(beanId, clazz) ?: throw NullPointerException("Spring Container not initialized")
}
