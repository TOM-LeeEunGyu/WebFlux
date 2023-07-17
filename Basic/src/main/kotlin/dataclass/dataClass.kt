package dataclass

/**
 * 자바에서 record가 등장하기 전에, DTO나 VO같이 어떤 정보를 담아서 전달하기 위한 객체를 정의해서 사용해왔다.
 * 이것을 위해서 hashCode나 equals, toString같은 메소드를 재정의해서 사용하기도 하고 불변성을 유지하기 위해서 getter만 정의해서 사용하곤 한다.
 * 하지만 롬복이 등장하면서 어노테이션 만으로도 쉽게 만들었고 불변객체로 만들기 위해 @Value 같은 어노테이션을 사용하게 된다.
 * 이런 것들을 쉽게 만들어서 사용할 수 있도록 등장한 것이 record 이다. record의 특징은 다음과 같다
 *
 * 1. abstract로 선언할 수 없고 기본적으로 final이기 때문에 다른 클래스가 상속할 수 없다.
 * 2. 또 다른 클래스를 상속할 수 없다.
 * 3. 인터페이스는 구현상속 가능하다.
 * 4. 접근 제어자는 public, package-private(default) 만 가능하다.
 * 5. 레코드의 컴포넌트는 암시적으로 final 이다.
 * 6. 레코드의 컴포넌트에 어노테이션을 사용할 수 있다.
 * 7. 클래스와 비슷하게 정적 필드와 생성자, 메소드, 중첩 클래스를 선언할 수 있다.
 * 8. equals로 동치성 비교시에 record가 가지는 특성으로 equals와 hashCode를 자동으로 재정의하기 때문에 true가 나온다.
 */

/**
 * 자바의 record가 있다면 코틀린에는 data class가 있다.
 *
 * 1. 하나 이상의 프로퍼티를 가지는 rimary constructor가 무조건 있어야 한다.
 * 2. 프로퍼티는 val 또는 var로 선언해야 한다.
 * 3. abstract, open, sealed, inner 키워드를 허용하지 않는다.
 * 4. sealed class를 상속할 수 있다.
 */

fun main() {
    val tshirts = Tshirts("nike", TshirtsType.HOOD_LONG, 100)
    with(tshirts) {
        println("brand: $brand, type: $type, size : $size")
        println("tshirts is ${tshirts.toString()}") // 클래스 내에서 재정의 하지 않아 객체의 메모리 주소가 찍힌다. (72ea2f77)
    }

    var sameTshirts = Tshirts("nike", TshirtsType.HOOD_LONG, 100)
    with(sameTshirts) {
        println("brand: $brand, type: $type, size : $size")
        println("sameTshirts is ${toString()}")
    }

    println("true or false? : ${tshirts.equals(sameTshirts)}") // false, data class 로 변경하면 true 출력( 위에서 언급했듯 data 클래스는 hashCode, toString, equals를 자동으로 재정의)

    val tshirts2 = Tshirts2("NIKE", TshirtsType.HOOD_LONG)
    tshirts2.sizeOf(100)
    println("티셔츠2임 , $tshirts2")
    println("티셔츠2의 사이즈임, ${tshirts2.size}")

    val sameTshirts2 = Tshirts2("NIKE", TshirtsType.HOOD_LONG)
    sameTshirts2.sizeOf(110)
    println("$sameTshirts")
    println("${sameTshirts.size}")

    println("true or false? : ${tshirts == sameTshirts}")

}

enum class TshirtsType(val description: String) {
    SHORT("반팔티"),
    LONG("긴팔티"),
    HOOD_SHORT("반팔 후드티"),
    HOOD_LONG("긴팔 후드티")
}

data class Tshirts(
    val brand: String,
    val type: TshirtsType,
    val size: Int,
)

/**
 * 코틀린의 재정의되는 컴포넌트와 copy 메소드는 생정자에 정의된 컴포넌트에 대해서만 가능하다.
 */

// 어떤 요구사항에서 브래늗와 티셔츠 타입이 같다면 이것은 동치 관계라고 표현할 수 있다. 즉 사이즈가 다르더라도 그 티셔츠는 같은 모델이라고 볼 수 있을 것이다.
data class Tshirts2(
    val brand: String,
    val type: TshirtsType,
) {
    var size: Int = 0
        private set

    fun sizeOf(_size: Int) {
        this.size = _size
    }

}

/**
 * 동반 객체
 * 자바의 static을 코틀린에서 사용해 보자
 */

class TshirtsEntity(
    val brand: String,
    val type: TshirtsType,
    val size: Int
)

data class Tshirts3(
    val brand: String,
    val type: TshirtsType,
    val size: Int,
) {
    companion object {
        // dto의 static 처럼 작동한다
        fun of(entity: TshirtsEntity) = with(entity) {
            Tshirts3(brand = brand, type = type, size = size)
        }
    }
}


