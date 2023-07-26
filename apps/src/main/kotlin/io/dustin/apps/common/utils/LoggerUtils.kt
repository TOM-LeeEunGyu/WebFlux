package io.dustin.apps.common.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 클래스별로 로거 설정할 수 있도록 Inline, reified를 통해 제너릭하게 사용해 보자.
 * created by basquiat
 *
 * @return Logger
 */
inline fun <reified T> logger(): Logger = LoggerFactory.getLogger(T::class.java)