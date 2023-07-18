package sealedclass

/** 상속계층 유형에서 제한된 하위 클래스를 정의하는데 사용된다
 * enum과 유사하지만 enum은 싱글 인스턴스로 존재한다면 sealed class는 하위 클래스가 sealed class를 통해 여러 상태를 가질 수 있다는 것이다.
 */

fun main() {
    Band.addMember(Keyboardist("test"))
    Band.addMember(Bassist("test"))
    Band.addMember(Vocalist("test"))
    Band.addMember(Guitarist("test"))
    Band.addMember(Drummer("test"))

    Band.members().forEach {player ->
        val playerType = when(player) {
            is Keyboardist -> "Keyboardist"
            is Drummer -> "Drummer"
            else -> "어떤 타입이 몇개나 있닌지 몰라 ~"
        }
        println("playerType : $playerType")
    }

}


sealed class Player(val name: String)
class Vocalist(name: String): Player(name)
class Keyboardist(name: String): Player(name)
class Guitarist(name: String): Player(name)
class Bassist(name: String): Player(name)
class Drummer(name: String): Player(name)

object Band {
    private val members = mutableListOf<Player>()

    fun addMember(player: Player) = members.add(player);

    fun members() = members
}