package inheritance

/**
 * 자바는 기본적으로 최상위 클래스 object를 기본적으로 상속하고 있는 형태이다.
 * 코틀린은 최상위 클래스를 object보다 any로 명명하고있다.
 * 코틀린 클래스를 생성하게 되면 기본적으로 final이 된다
 * 만약 A라는 클래스가 B라는 클래스와 C,D 인터페이스를 상속한다면
 * class A: B(), C, D 이렇게 된다 (클래스는 생성자 명시해야 한다)
 */


fun main() {
    val samsung = Samsung(0,"a 시리즈","한국")
    samsung.kind()
}

class Samsung(
    var price: Int = 1000000,
    seriesName: String,
    manufacturing: String,
    override var type: PhoneType = PhoneType.FOLDER // 삼성은 폴더블 폰 가능

): Phone(seriesName, manufacturing) {
    override fun kind() {
        println(" s 시리즈 입니다.")
    }
    // override fun discontinuedPhone() open 되어 있지 않으므로 오버라이딩 할 수 없다.
}

class Apple(
    var price: Int = 150000,
    seriesName: String,
    manufacturing: String,

) : Phone(seriesName, manufacturing) {
    override var type: PhoneType = PhoneType.NORMAL // 애플은 접는것 없다.
    override fun kind() {
        println(" 14 입니다. ")
    }
}

/** 상속을 고려하고 설계한 클래이고, 하위 클래스에서 오버라이드를 허용하려면 open 키워드를 사용하고 그렇지 않다면 그대로 놔두면 된다. */
open class Phone(
    var seriesName: String,
    var manufacturing: String
) {

    open fun kind() {
        println("휴대폰 종류는 무엇입니까 ?? ")
    }

    open val type: PhoneType = PhoneType.NORMAL

    fun discontinuedPhone(){
        println("더 이상 출시되지 않는 휴대폰, 오버라이드 안됨 ")
    }
}

/**
 * 상위 클래스의 생성자가 아닌 바디에 선언된 프로퍼티를 상속하는 방식은 두 가지 방식이 있다.
 * 생성자를 통해 프로퍼티를 상속하겠다면 Samsung 처럼 생성자 안에서 override 키워드로 작성한다
 * 또는 apple 처럼 바디 내에서 상속이 가능하다.
 * 중요한 부분
 * 상위 클래스의 변수 타입이 val 이라고 해도 하위 클ㄹ래스에서는 var/val 로 선언해서 받을 수 있다.
 * 하지만 상위클래스의 변수 타입이 var 라면 하위 클래스에서 val 로 받을 수 없다.
 */
enum class PhoneType{
    FOLDER,
    NORMAL

}

/** 추상 클래스 상속 방법 */
fun main2() {
    val main = AbstractMain("Test", "abstract")
 }


class AbstractMain(name: String, override var type: String) : AbstractTest(name) {

    override fun play(inst: String) {
        println("자바랑 코틀린이랑 헷갈리기 시작한다.")
    }

}

abstract class AbstractTest(
    var name: String
) {
    abstract val type: String
    abstract fun play(inst: String)
}



/** 인터페이스 상속 방법
 * 추상클래스보다는 인터페이스를 우선하라
 * 인터페이스는 구현하는 쪽을 생각해 설계하라
 * 인터페이스는 타입을 정의하는 용도로만 설계하라
 * 상수 인터페이스 안티패턴은 인터페이스를 잘못 사용한 예다.
 * - (이펙티브 자바)
 * */

fun main3() {
    val parent = Parent()
    with(parent) {
        myIntProp = 10000000
        myStrProp = "1000000"
    }
}


class Parent(override var myIntProp: Int = 0): MyInterface {

    // 구현 상속한 곳에서는 backing field 접근이 가능
    // 인터페이스의 값을 그대로 사용하겠다면 super로 접근 가능
    override var myStrProp: String = super.myStrProp
    // 인터페이스는 backing field를 가질 수 없다.
    // 하지만 상속한 하위 클래스에서느느 backing field를 사용할 수 있다.
    //get() {
    //    return field.uppercase()
    //}

    override fun myFun() {
        println("MY")
    }

}

interface MyInterface {

    /**
     * 코틀린의 인터페이스 내의 프로퍼티들은 backing field를 가질 수 없고 초기화로 값을 할당할 수 없다.
     * 그래서 초기값을 할당하기 위해서는 getter로 할당한다.
     * 단 상속구현하는 하위 클래스에서는 오버라이딩한 프로퍼티는 backing field가 지원된다.
     * var 타입으로는 인터페이스에서 프로퍼티를 만들 수 없다.
     * 결론: 하위 클래스에서 무조건 상속 이후 초기값을 설정 해줘야 한다.
     */


    val myIntProp: Int
        get() {
            return 0
        }


    val myStrProp: String
        get() {
            return "before"
        }


    fun myFun()
    fun myDefaultFun() {
        println("default method")
    }
}

interface Phone1 {
    fun turnOff()

    fun turnOn()

}

class IPhoneX14: Phone1 {

    override fun turnOff() {
        println("IPhoneX14을 끈다")
    }

    override fun turnOn() {
        println("IPhoneX14을 킨다")
    }

}

/** 혼자 만들어 보기 */

// 접었다 펼 수 있는 휴대폰
interface Folderable {

    fun call()

    fun beforeAction() {
        println("폴더를 연다.")
    }

}

// 일반 휴대폰
interface Normal {

    fun play()

    fun beforeAction() {
        println("폴더폰 아니다.")
    }

}


// 1. 폰을 켠다.
// 2. 폰을 폴더플인 폴더를 열고, 킨다.
// 3. 전화를 한다.

class LG(
    var price:Int,
    var phoneName:String
): Phone1, Folderable, Normal {
    override fun turnOn() {
        TODO("Not yet implemented")
    }

    override fun turnOff() {
        TODO("Not yet implemented")
    }

    override fun call() {
        super<Folderable>.beforeAction()
        println("전화를 건다.")
    }

    override fun play() {
        super<Normal>.beforeAction()
        println("게임을 한다.")
    }

    override fun beforeAction() {
        TODO("Not yet implemented")
    }

}

























