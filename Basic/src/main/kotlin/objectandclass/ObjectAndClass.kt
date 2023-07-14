package objectandclass

import java.lang.reflect.Constructor

/**
 * public: 모든 접근을 허용
 * private: 현재 객체 내에서만 허용
 * protected: 같은 패키지에 있는 상속관계 객체들만 허용
 * internal: 같은 모듈 내에서 접근 가능
 */

/** 코틀린의 객체 생성방식
 * 주 생성자와 부 생성자
 */

// {}가 없어도 된다.
class NoBodyClass

// {}가 있어도 상관없다.
class BodyClass{}

fun main() {
    val dustin1 = Dustin()
    dustin1.name = "giwoon";
    dustin1.age = 26;
    with(dustin1) {/** 객체를 입력받아 프로퍼티를 직접 사용한다, 주로 객체의 함수를 여러 개 호출할 때 그룹화하는 용도로 활용된다. */
        println(" I am : $name | $age")
    }

    val dustin2 = Dustin("giwoon", 26);
    with(dustin2) {
        println(" I am : $name | $age")
    }

    val dustin = PrimaryDustin("giwoon", 26);
    with(dustin) {
        println(" I am : $name | $age")
    }
}
// 부 생성자로 생성한 클래스: 주 생성자의 매개 변수에 디폴트 값을 설정할 수 있기 때문에 여러개의 생성자를 만들 필요성이 적지만, 매개 변수의 타입이 다른 경우 등 필요한 경우가 있을 수도 있다.
class Dustin {
    var name: String? = null;
    var age: Int? = null;

    /** 부 생성자를 만들 경우에 내부 프로퍼티의 이름이 같으면 가독성 차원에서 관례적으로 _를 붙여준다. */
    constructor(_name: String, _age: Int) {
        this.name = _name
        this.age = _age
    }
    constructor(_name: String) {
        this.name = _name;
    }

    constructor() {

    }
}

class PrimaryDustin(
    var name: String,
    var age: Int,
)


/**
 * 주 생성자로 인자있는 생성자를 만들고 부생성자로 인자가 없는 기본 생성자처럼 흉내낸다 ?
 */
class Test(
    var name: String,
    var age: Int,
) {
    /** 주 생성자로 생성자를 만들 때엔 기본값을 줘야 오류가 발생하지 않는 것 같다. */
    constructor() : this("",0)
    constructor(_age: Int) : this(name= "", age = _age);

}


/**
 * var 와 val
 * var는 접근 및 재할당이 가능하고, val은 재할당이 불가능하다. 이에 따라 객체에서는 자동으로 getter/setter를 생성해준다.
 * 즉 var은 자동으로 setter/getter가 그리고 val은 getter만 존재하게 된다.
 */

fun main2() {
    val valObject = ValObject("giwoon");
//    valObject.name = "donghoon"   -> 불가능


}

class ValObject (
    val name: String,
) {
   val genre: String = "test"
       get() {
           return this.genre.lowercase() // this.genre 그 자체가 getter를 호출하게 될 것이고 뮤지션의 객체의 커스텀 getter를 호출하게 되고 그게 무한 재귀로 빠져든다.
           return field.lowercase()
       }

}
/**
 * var는 자동으로 getter와 setter를 만들어준다.
 * 바디 내에 선언되는 프로퍼티는 val과 같이 초기값을 설정해줘야 한다.
 */


class VarObject(
    var name: String,
) {
    var genre: String? = null
        private set
    fun changeGenre(_genre: String?) {
        // 메소드를 호출할 때 Cannot assign to 'genre': the setter is private 발생
        this.genre = _genre
    }

}


