package myfuntion

fun myFuntion() {

    /** 자바 스크립트처럼 클래스가 없더라도 탑 레벨에서 정의가 가능하고 함수 안에서 함수를 정의할 수 있다. */
    fun test(): Unit {
        println("난 테스트")
    }

    hello()
    test()
    helloWithParam("거기 누구 있어요?")
    helloWithParam()

}

// 디폴트를 줌
fun helloWithParam(message: String = "거기 아무도 없어요?") {
    println(message)
}
fun hello(): Unit {
    println("반갑다")

    fun innerHello(): Unit {
        println("내부에서 반갑다")
    }
    innerHello()
}

fun getStr(): String {
    return "string"
}
fun sum(a: Int, b: Int): Int {
    return a + b
}

// 표현식으로 작성하면 반환 타입 생략 가능
fun getStrExpression() = "string expression"
fun sumExpression(a: Int, b: Int) = a + b


/**
 * Unit 과 Nothing
 * 코틀린에서 제공하는 타입 중 데이터와 관계 없이 특수 상황을 표현하기 위한 Unit 과 Nothing 타입이 있다. Unit는 반환 구분이 없다고 표현하기 위해 사용된다(void)
 * Nothing은 의미 있는 데이터가 없다는 것을 명시적으로 선언하기 위해 사용하는 타입니다
 * fun myFun(arg: Nothing?): Nothing { throw Exception() }
 */