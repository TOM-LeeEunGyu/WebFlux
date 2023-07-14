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
    manufacturing: String

): Phone(seriesName, manufacturing) {
    override fun kind() {
        println(" s 시리즈 입니다.")
    }
    // override fun discontinuedPhone() open 되어 있지 않으므로 오버라이딩 할 수 없다.
}

/** 상속을 고려하고 설계한 클래이고, 하위 클래스에서 오버라이드를 허용하려면 open 키워드를 사용하고 그렇지 않다면 그대로 놔두면 된다. */
open class Phone(
    var seriesName: String,
    var manufacturing: String
) {

    open fun kind() {
        println("휴대폰 종류는 무엇입니까 ?? ")
    }

    fun discontinuedPhone(){
        println("더 이상 출시되지 않는 휴대폰 ")
    }
}

enum class PhoneType{
    FOLDER,
    NORMAL

}
