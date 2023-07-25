package io.dustin.apps.common.convert

/**
 * out T
 * 인터페이스를 상속받는 클래스들을 전달받는 것을 허용해준다.
 * read 만 가능하다
 *
 */
interface InterfaceGenericEnum<out T> {
    fun defaultIfNull(): T
}