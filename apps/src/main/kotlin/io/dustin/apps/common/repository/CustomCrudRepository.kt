package io.dustin.apps.common.repository

import io.dustin.apps.common.exception.DataNotFoundException
import io.dustin.apps.common.utils.dataNotFound
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull


/**
 * 코틀린의 확장함수
 * 자바스크림트에 prototype 이라는 것이 있다. prototype을 이용하면 기존 객체에 새로운 함수를 추가할 수 있따.
 * ex)
 * Array.prototype.lastElem = function() {
 *      return this[this.length - 1];
 * }
 *
 * let array = [5,4,3,2,1, "last"];
 * console.log(array.lastElem());
 *
 * 이처럼 코틀린에서도 기존에 있는 객체를 커스텀해서 사용할 수 있다.
 */
fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID, message: String? = null): T = this.findByIdOrNull(id) ?: dataNotFound()