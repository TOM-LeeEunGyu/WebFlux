package controlflow

fun controlflow() {

    /**
     * 코틀린은 삼항연산자가 없다. 대신 표현식으로 제공한다
     */
    var str: String = "test"

    val newType = if("test".equals(str)) 10 else 5 // 파이썬과 비슷한 것 같다.

    val num: Int = 4;

    val numCheckResult: String = if(num > 10) "십보다 큼" else if(num >= 5 && num <= 10) "중간값임 " else "작은값임"

    /**
     * when
     * switch case 문과 같은 원리라고 이해했다.
     */
    var name: String = ""
    when(name) {
        "giwoon" -> name = name.uppercase()
        "donghoon" -> name = name.uppercase()
        else -> name = "ETC"
    }

    // 리턴 when 표현식
    var resultName = when(name) {
        "giwoon" -> name.uppercase()
        "donghoon" -> name.uppercase()
        else -> "Etc"
    }

    /** 최근에 가장 많이 사용하는 enum을 사용해보자 */
    var defaultName = Name.GIWOON // enum을 변수에 할당할 때에는 타입을 지정하지 않아도 된다.
    var result = when(defaultName) {
        Name.GIWOON -> "기운"
        Name.DONGHOON -> "동훈"
        else -> "기타등등"
    }

    /** 자바처럼 enum을 묶어서 처리 */
    var nameList = when(defaultName) { Name.GIWOON, Name.DONGHOON -> " 기운동훈 " else -> "기타등등"}

    /**
     * loop를 사용하기 위해서는 코틀린의 Range 표현 방식을 알아야 한다.
     * 1..10 -> 1 부터 10 까지
     */
    for(i in 1..10){
        println(i)
    }
    for(i in 1.rangeTo(10)){
        println(i)
    }
    // until의 경우 (index = 0; i < 11; i++)
    for(i in 1 until 11){
        println(i)
    }
    for(char in 'a'..'f'){
        println(char)
    }
    for(char in 'a'.rangeTo('f')){
        println(char)
    }

    /** loop에 간격을 주기  for i in range(1,10,2) */
    for(i in 1..10 step 2){
        println(i)
    }
    for(char in 'a'..'f' step 2){
        println(char)
    }

    /** 줄어드는 loop */
    for(i in 10 downTo 5){

    }
    for(i in 10.downTo(5)){
        println(i)
    }

    /** 배열 돌기 */
    val array = arrayOf(1,2,3,4,5,6)
    for(i in array) {
        println(i)
    }

    // while, do while 등은 다른 언어와 동일하여 패스한다.
}

enum class Name {
    GIWOON,
    DONGHOON,
    ETC

}



