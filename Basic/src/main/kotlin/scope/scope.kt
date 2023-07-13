/**
 * let
 * fun <T, R> T.let{block: (T) -> R}: R
 * 또한 T?.let { } 형태에서의 let 블럭 안에는 non-null 만 들어올 수 있어 non-null 체크 시에 유용하게 쓸 수 있다. 객체를 선언하는 상황일 경우에는 elvis operator(?:) 를 사용해서 기본값을 지정해줄 수도 있다.
 */
class Person(
    var name: String,
    var age: Int
)

class Monster(
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
    "${it.name} is ${it.age}"
}

var resultUnit = person.let {
    it.name = "donghoon"
    it.age =26
}

// Person(name=James, age=56)
// Steve is 59
// kotlin.Unit

/**
 * with
 * fun <T, R> with(receiver: T, block: T.() -> R): R
 * 일반 함수이기 때문에 객체 receive를 직접 입력받고 객체를 사용하기 위한 두 번째 파라미터 블럭을 받는다. T.()를 람다 리시버라고 하는데, 입력을 받으면 함수 내에서 this를 사용하지 않고도 입력받은 객체(receiver)의 속성을 변경할 수 있다.
 * 즉, 아래 예제에서 with(T) 타입으로 Person을 받으면 {} 내의 블럭 안에서 곧바로 name 이나 age 프로퍼티에 접근할 수 있다. with는 non-null의 객체를 사용하고 블럭의 return 값이 필요하지 않을 때 사용한다. 그래서 주로 객체의 함수를 여러 개 호출할 때 그룹화하는 용도로 활용된다.
 */
val person2 = Person("kim", 26)
val monster2 = Monster("kim", 26)

val result2 = with(person2) {
    println(name)
    println(age)
    //자기자신을 반환해야 하는 경우 it이 아닌 this를 사용한다
}

/**
 * run
 * fun <T, R> T.run(block: T.() -> R): R
 * with 처럼 인자로 람다 리시버를 받고, 반환 형태도 비슷하게 생겼지만 T의 확장함수라는 점에서 차이가 있다. 확장함수이기 때문에 safe call(.?) 을 붙여 non-null 일 때에만 실행할 수 있다.
 * 어떤 값을 계싼할 필요가 있거나 여러 개의 지역변수 범위를 제한할 때 사용한다
 */
val person3 = Person("kim", 26)
val result3 = person3.run {
    age ++
}

/** fun <R> run(block: () -> R): R */
/** 이 run은 확장 함수가 아니고, 블럭에 입력값도 없다. 따라서 객체를 전달받아서 속성을 변경하는 형식에 사용되는 함수가 아니다. 이 함수는 어떤 객체를 생성하기 위한 명령문을 블럭 안에 묶음으로써 가독성을 높이는 역할을 한다. */
val person4 = run {
    val name = "James"
    val age = 56
    Person(name, age)
}

/***
 * apply
 * fun <T> T.apply(block: T.() -> Unit): T
 * T의 확장함수이고, 블록 함수의 입력을 람다로 리시버로 받았기 때문에 블럭 안에서 객체의 프로퍼티를 호출할 때 it이나 this를 사용할 필요가 없다. run과 유사하지만 블럭에서 return 값을 받지 안흥며 자기 자신인 T를 반환한다.
 * 객체의 함수를 사용하지 않고 자기 자신을 다시 반환할 때 사용되기 때문에, 객체의 초기화나 변경 시에 주로 사용된다
 */
val person5 = Person("", 0);
val result5 = person5.apply {
    name = "giwoon"
    age = 26
}


/**
 * also
 * T의 확장함수이고, 블럭 함수의 입력으로 람다 리시버를 받지 않고 this로 받았다. 마찬가지로 T를 반환한다.
 * 블럭 함수의 입력으로 T를 받았기 때문에 it을 사용해 프로퍼티에 접근하는 것을 볼 수 있다. 그래서 객체의 속성을 전혀 사용하지 않거나 변경하지 않고 사용하는 경우에 also를 사용한다.
 * 객체의 데이터 유효성을 확인하거나, 디버그, 로깅 등의 부가적인 목적으로 사용할 때에 적합하다.
 * apply와 also는 자기 자신을 리턴한다는 점에서 Builder 패턴과 동일한 용도로 사용된다.
 */
val person6 = Person("", 0)
val result = person6.also {
    it.name = "James"
    it.age = 56
}
