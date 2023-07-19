package enumclass

/**
 * 코틀린에서 data class는 생성자 매개변수를 가져야 합니다. data class는 컴파일러가 자동으로 생성자, equals(), hashCode(), toString() 등을 생성해주는 특별한 클래스 유형입니다.
 * 따라서 data class를 선언할 때 생성자 매개변수가 필요합니다.
 */

// 아무것도 못탐
data class NoVehicle(val name: String): Vehicle {
    override fun vehicleType() {
        TODO("Not yet implemented")
    }
}

// 60퍼
data class Land60PercentVehicle(val name: String): Vehicle {
    override fun vehicleType() {
        TODO("Not yet implemented")
    }
}

// 120퍼
data class Land120PercentVehicle(val name: String): Vehicle {
    override fun vehicleType() {
        TODO("Not yet implemented")
    }
}

// 날아다니는 것
data class FlyingVehicle(val name: String): Vehicle {
    override fun vehicleType() {
        println("150프로의 이동속도를 가지는 날것")
    }
}

sealed interface Vehicle {
    fun vehicleType() {

    }
}