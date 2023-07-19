package enumclass


/**
 * enum 특징
 * 열거 타입 자체는 클래스이다. 생성자와 메소드를 가질 수 있다.
 * 상수 하나당 자신의 인스턴스를 하나씩 만들어 pubic static final 필드로 공개한다.
 * 열거 타입은 밖에서 접근할 수 있는 생성자를 제공하지 않으므로 사실상 final 이다.
 * 이로 인해 클라이언트가 인스턴스를 직접 생성하거나 확장할 수 없으니 열거 타입 선언으로 만들어진 인스턴스들은 딱 하나씩만 존재함이 보장된다.
 * 열거 타입은 싱글턴을 일반화한 혈태라고 볼 수 있다.
 * 열거 타입은 컴파일타임 타입 안정성을 제공한다.
 * 열거타입 상수는 인스턴스 필드를 가질 수 있다.
 */

//fun main() {
//    val myEnum = MyEnum.ENUM_ONE
//    with(myEnum) {
//        println("enum name: $name, ordinal: $ordinal")
//    }
//}
//
//enum class MyEnum {
//    ENUM_ONE,
//    ENUM_TWO,
//    ENUM_THREE,
//}

/** enum 클래스로 와우를 만들어 보자
 *  40 레벨 미만이면 탈것을 얻을 수 없고, 40 레벨 이상 60 레벨 미만이면 60% 이동 속도의 탈것을 얻을 수 있다.
 *  60 레벨 이상 70 레벨 미만이면 120% 이동 속도의 탈것, 70 레벨 이상이면 날아다니는 것을 얻을 수 있다.
 */

fun main() {
    val warrior = Character("rlaejrqo465@naver.com", "dusitn",CharacterJob.WARRIOR)
    with(warrior) {
        println(" dustin info : id : $id, name : $name, job : ${job.jobName}, level : $level")
    }

    // 한 마리 당 1업
    for(level in 1..30){
        warrior.levelUp()
    }
    with(warrior) {
        println(" dustin info : id : $id, name : $name, job : ${job.jobName}, level : $level")
    }

//    val possibleVehicle = if(warrior.level in 1.until(40)) {
//        NoVehicle()
//    } else if(warrior.level in 40.until(60)) {
//        Land60PercentVehicle()
//    } else if(warrior.level in 60.until(70)) {
//        Land120PercentVehicle()
//    } else {
//        FlyingVehicle();
//    }
//    possibleVehicle.vehicleType()


}

enum class LevelStatus(
    val description: String,
    val levelCheck: (level:Int) -> Boolean,
) {
    LT40("40레벨미만", { level -> level in 0.until(40) }),
    GTE40LT60("40레벨이상이고 60레벨 미만", { level -> level in 40.until(60)}),
    GTE60LT70("60레벨이상이고 70레벨 미만", { level -> level in 60.until(70)}),
    GTE70("70레벨이상", { level -> level in 70..Int.MAX_VALUE});

    companion object {
        // 레벨로 열거타입을 변환하는 정적 메소드
        fun levelStatusFromLevel(level:Int): LevelStatus = values().firstOrNull{ levelStatus -> levelStatus.levelCheck(level) }
            ?: throw IllegalArgumentException("캐릭터의 레벨 정보가 이상합니다.")
    }

}

//class Character(
//    val id: String,
//    val name: String,
//    val job: CharacterJob,
//    var level: Int = 1, // 레벨은 변경 될 수 있으니 var
//)

// 캐릭터 클래스 리팩토링
class Character(
    val id: String,
    val name: String,
    val job: CharacterJob,
) {
    var level = 1

    var levelStatus = LevelStatus.LT40

    fun levelUp() {
        ++level
        levelStatus = LevelStatus.levelStatusFromLevel(level);

        // enum 클래스에서 람다식을 사용했기 때문에 이부분은 없어도 됨
//        levelStatus = when(level) {
//            in 1.until(40) -> {
//                LevelStatus.LT40
//            }
//            in 40.until(60) -> {
//                LevelStatus.GTE40LT60
//            }
//            in 40.until(70) -> {
//                LevelStatus.GTE60LT70
//            }
//            else -> {
//                LevelStatus.GTE70
//            }
//        }
    }
}



enum class CharacterJob(
    val jobName: String,
) {
    WARRIOR("전사"),
    HUNTER("사냥꾼"),
    MAGICIAN("마법사"),
    CLERIC("성직자"),
    ROGUE("도둑")
}