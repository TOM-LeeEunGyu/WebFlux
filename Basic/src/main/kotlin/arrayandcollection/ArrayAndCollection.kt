package arrayandcollection

/**
 * 자바 배열의 특징
 * 배열의 크기는 고정되어 있다.
 * 크기는 고정되면 변경할 수 없다.
 * 인덱스로 배열의 요소에 접근 가능하다
 * 배열은 기본적으로 mutalbe 하다. 따라서 인덱스로 배열의 요소를 변경 가능하다 .
 */

/** 코틀린의 배열 */

fun arrayTest() {

    // 배열 크기가 고정된다.
    val intArrays: Array<Int> = arrayOf(1, 2, 3)

    // Array의 생성자를 보면 마지막 인자가 람다이다.
    // 후행 람다 형식으로 표현가능하고 이떄 넘어온 it은 인덱스 정보가 넘어온다.
    val intArraysLambda: Array<Int?> = Array(4) { it }
    for (value in intArraysLambda) {
        println("intArraysLambda is $value")
    }

    // 배열크기를 4로 고정하고 null로 채운다.
    val intArraysDefaultNull: Array<Int?> = Array(4) { null }
    for (value in intArraysDefaultNull) {
        println("intArraysDefaultNull is $value")
    }

    // 이것은 위에 방식을 API를 이용해서 생성할 수 있다.
    val intArraysWithNull: Array<Int?> = arrayOfNulls(4)
    for (value in intArraysWithNull) {
        println("intArraysWithNull is $value")
    }

    val arrayOf: Array<Int> = arrayOf(1, 2, 3, 4, 5)
    // set으로 접근해도 되지만 IDE에서는
    // 밑의 [index] 방식을 추천한다.
    arrayOf.set(0, 100)
    println(arrayOf[0])
    arrayOf[0] = 200
    println(arrayOf[0])

    val empty: Array<Int> = emptyArray() // null을 반환하기 보다는 빈 리스트나 빈 배열을 보내주는 것이 좋다 - 이펙티브 자바

}

/**
 * 리스트
 * 코틀린의 리스트는 불변과 가변으로 나누어진다.
 */

fun listTEst() {
    // 기본적으로 immutable 이다.
    val strList: List<String> = listOf("a","b")
    for(value in strList) {
        println("strList is $value ")
    }
    val checked = strList.contains("c");
    println("checked is $checked");
    // strList[0] = "c" 오류발생

    // null을 허용하지 않는 리스트도 존재한다.
    val notNullList: List<String> = listOfNotNull("a", null, "c", null, null, "d")
    println(notNullList.size)
    for(value in strList) {
        println("notNullList is $value")
    }

    val listEmpty: List<String> = emptyList()

    // 가변(mutable List)
    val mlo = mutableListOf<String>()
    mlo.add("a")
    val alo = arrayListOf<String>()
    alo.add("aa")
    println(mlo)
    println(alo)
    mlo[0] = "aaaaa"
    alo[0] = "aaaaaaaaaaaaa"
    println(mlo)
    println(alo)

    // API가 아닌 직접 MutableList로 생성할 수 있다.
    val direct = MutableList(5) { "a[${it}]" }
    println(direct)
    direct.add("direct")
    println(direct)

}




































