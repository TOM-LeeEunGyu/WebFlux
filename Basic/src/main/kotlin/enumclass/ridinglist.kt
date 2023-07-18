package enumclass

// 아무것도 못탐
class NoVehicle: Vehicle {
    override fun vehicleType() {
        TODO("Not yet implemented")
    }
}

// 60퍼
class Land60PercentVehicle: Vehicle {
    override fun vehicleType() {
        TODO("Not yet implemented")
    }
}

// 120퍼
class Land120PercentVehicle: Vehicle {
    override fun vehicleType() {
        TODO("Not yet implemented")
    }
}

// 날아다니는 것
class FlyingVehicle: Vehicle {
    override fun vehicleType() {
        println("150프로의 이동속도를 가지는 날것")
    }
}






sealed interface Vehicle {
    fun vehicleType()
}