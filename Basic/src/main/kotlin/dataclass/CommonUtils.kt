package dataclass

/**
 * 정적 메소드로 구성된 유틸성 클래스를 코틀린에서는 어떻게 구현할까 ?
 * 1. 탑 레벨에 정의한다
 *  - 자바스크립트 처럼 함수를 탑 레벨에서 정의할 수 있다.
 * 2. 싱글톤 객체로 유틸 클래스를 정의하자.
 *  - private 생성자나 열거 타입으로 싱글톤을 보증하라(이펙티브 자바)
 */

/** 자바에서 싱글톤 객체를 만드는 방법 */
//public class Elvis {
//
//    private Elvis() {}
//
//    public static Elvis getInstance() {
//        return ElvisHolder.INNER_ELVIS_INSTANCE;
//    }
//
//    // ContextHolder라든가 하는 이런 말들 많이 봤을텐데 Holder라는 말은 일종의 관용처럼 쓰인다.
//    // 즉 바로 어떤 객체를 메모리로 올리지 않고 이 holder가 호출되는 시점에 보유한 객체를 생성해서 반환한다는 의미로 봐도 될까?
//    private static class ElvisHolder {
//
//        private static final Elvis INNER_ELVIS_INSTANCE = new Elvis();
//
//        private ElvisHolder() {
//            super();
//        }
//    }
//
//}

/** 코틀린에서 싱글톤 객체를 만드는 방법 */
object CommonUtils {
    fun test() {
        println("코틀린의 싱글톤은 너무 간단하다.")
    }
}