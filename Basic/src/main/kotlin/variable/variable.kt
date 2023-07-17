/**
 * 자바에는 원시타입과 래퍼 클래스가 존재한다. 하지만 코틀린은 래퍼 클래스만 존재한다. 코틀린에서는 기본적으로 non-null Type 이다.
 */
fun variable() {
    // var canNotBeUsed: String = null 사용할 수 없다

    var nu: String? = "Test" // null 을 세팅하고 싶을때는 ?를 통해 nullable 하다고 알려줄 수 있다. 변수를 메소드 파라미터로 사용할 때 역시 알려줘야 한다
    var nu2: String = "Test2"
    variableNullable(nu!!) // '!!'를 통해서 null 일 수 없다고 확신한다고 명시를 해서 처리할 수 있다.
    methodParameterNullable(nu2) //
}

fun variableNullable(str: String?) {
    println(str)
}

// 변수는 nullable 하지 않지만  함수의 파라미터에서 nullable 을 처리하는 안정적인 방식이 가능하다
fun methodParameterNullable(str: String?) {
    println(str)

}

/**
 * 코틀린의 대표적인 변수 두가지
 * val: value의 약어로 일종의 const 또는 final이 붙은 변수. Immutable(변경 불가능). 한번 할당하면 변경하지 못하지만 지연 초기화 사용 가능
 * var: variable의 약어로 가변변수라는 의미를 가지고 있으며 자바스크립트에서는 let과 같은 의미이로 자바에서는 일반 변수를 의미한다.
 */
fun varTest() {
    var a: String = "test"
    println(a)

    a = "change"
    println(a) // change

    // a = 0 error
}


