fun main() {
    var kim = returnKim(true)
    kim?.let{
        println(it.name) ?: throw IllegalArgumentException("null이여")
    }
}

fun returnKim(areYouKim: Boolean = false): Kim? = if(areYouKim) {
    /**
     * returnKim 함수: 이 함수는 areYouKim이라는 Boolean 타입 매개변수를 받습니다. 기본값은 false
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
