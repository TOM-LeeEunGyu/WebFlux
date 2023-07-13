fun main() {
    var kim = returnKim(true)
    kim?.let{
        println(it.name) ?: throw IllegalArgumentException("null이여")
    }
}

fun returnKim(areYouKim: Boolean = false): Kim? = if(areYouKim) {
    /**
     * returnKim 함수: 이 함수는 areYouKim이라는 Boolean 타입 매개변수를 받습니다. 기본값은 false
     *
     * 반환 타입: Kim? (nullable Kim 객체를 반환)
     * 동작: 만약 areYouKim == true 이면 Kim("kim") 객체를 생성하여 반환합니다. 그렇지 않으면 null 반환합니다.
     */
    Kim("kim")
} else {
    null
}


class Kim(
    var name: String
)


/**
 * 알아두고 가기
 * 코틀린의 let 함수란 무엇인가 ?
 * 나는 일단 자바의 functional interface 처럼 이해했다. (추후 더 공부를 하게되면 다르게 느낄수도 있다)
 * fun <T, R> T.let{block: (T) -> R}: R
 * 또한 T?.let { } 형태에서의 let 블럭 안에는 non-null 만 들어올 수 있어 non-null 체크 시에 유용하게 쓸 수 있다. 객체를 선언하는 상황일 경우에는 elvis operator(?:) 를 사용해서 기본값을 지정해줄 수도 있다.
 */
class Person(
    var name: String,
    var age: Long
)

var person = Person("",0)

var resultIt = person.let {
    it.name = "giwoon"
    it.age = 26
    it // (T) -> R 부분에서의 R에 해당하는 값 반환
}

var resultStr = person.let {
    it.name = "gihoon"
    it.age = 28
    "{$name is $age}"
}

var resultUnit = person.let {
    it.name = "donghoon"
    it.age =26
}

// Person(name=James, age=56)
// Steve is 59
// kotlin.Unit


