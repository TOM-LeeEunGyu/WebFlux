package io.dustin.common.transaction

import org.springframework.stereotype.Component
import org.springframework.transaction.reactive.TransactionalOperator
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * webflux에서 @Transaction를 사용할 때 이슈가 있다. 스프링 프레임워크 이슈카드를 해석 해보면
 *
 * "Spring Test Context Framework의 TransactionTestExecutionListener는 ThreadLocal에 바인딩된 트랜잭션 상태를 가진 Spring의 PlatformTransactionManager만 지원하기 때문에 현재 가능하지 않습니다."
 * 실제로 Reactive 프로그래밍에서는 단일 스레드로 동작하기에, ThreadLocal이라는 클래스를 사용할 수 없고 애초에 필요가 없다. R2DBC는 이를 위해 Context라는 객체를 이용하여 각 Flow에 대한 상태를 관리하는데, Junit에서는 이러한 Context에 대한 지원이 완벽하지 않다는 문제가 있었습니다
 * (라고 쓰여있다 따라서 이를 해결하기 위해 Transaction 클래스를 만들어 사용한다)
 */
@Component
class Transaction(
    transactionalOperator: TransactionalOperator
) {
    init {
        Companion.transactionalOperator = transactionalOperator
    }
    companion object {
        lateinit var transactionalOperator: TransactionalOperator
        fun <T> withRollback(publisher: Mono<T>): Mono<T> {
            return transactionalOperator.execute { tx ->
                tx.setRollbackOnly()
                publisher
            }.next()
        }
        fun <T> withRollback(publisher: Flux<T>): Flux<T> {
            return transactionalOperator.execute { tx ->
                tx.setRollbackOnly()
                publisher
            }
        }
    }
}