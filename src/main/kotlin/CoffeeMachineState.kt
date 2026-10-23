import CoffeeMachine.currentState
import CoffeeMachine.monedero
import CoffeeMachine.seleccion
import org.example.Bebidas

sealed class CoffeeMachineState {
    object Idle : CoffeeMachineState(){
        fun onenter() {
            println("Entrando al estado Idle")
            Thread.sleep(2000)
            if (CoffeeMachine.monedero > 0.0) {
                println("Monedero detectado, pasando a Standby")
                currentState = Standby
            }
        }
        override fun toString(): String {
            return "Idle(monedero=${CoffeeMachine.monedero})"
        }
    }
    object Standby : CoffeeMachineState(){
        fun onenter() {
            println("En Standby, cafe $seleccion seleccionado")
            if (seleccion == Bebidas.CORTO && seleccion.precio <= monedero) {
                currentState = CafeCorto
            }
            else if (seleccion == Bebidas.LECHE && seleccion.precio <= monedero) {
                currentState = CafeConLeche
            }
            else if (seleccion == Bebidas.AMERICANO && seleccion.precio <= monedero) {
                currentState = CafeAmericano
            }
            else {
                println("No tiene saldo suficiente para $seleccion,volviendo a Idle")
                currentState = Idle
            }
        }

        override fun toString(): String {
            return "Standby"
        }
    }
    object CafeCorto : CoffeeMachineState() {
        fun onenter() {
            println("Cafe corto en preparacion...")
            Thread.sleep(5000)
            currentState = CafeListo
        }

        override fun toString(): String {
            return "Cafe Corto"
        }
    }

    object CafeConLeche : CoffeeMachineState(){
        fun onenter() {
            println("Cafe con leche en preparacion...")
            Thread.sleep(5000)
            currentState = CafeListo
        }

        override fun toString(): String {
            return "Cafe con Leche"
        }
    }
    object CafeAmericano : CoffeeMachineState(){
        fun onenter(){
            println("Cafe americano en preparacion...")
            Thread.sleep(5000)
            currentState = CafeListo
        }

        override fun toString(): String {
            return "Cafe americano"
        }
    }
    object CafeListo : CoffeeMachineState(){
        fun onenter(){
            println("Café servido. Por favor, toma tu café.")
            val cambio = monedero - 0.60
            println("Cambio a percibir: $cambio")
            currentState = Limpiando
        }

        override fun toString(): String {
            return "Cafe Listo"
        }
    }
    object Limpiando : CoffeeMachineState(){
        fun onenter(){
            println("La máquina está limpiándose. Espera a que termine.")
            Thread.sleep(2000)
            print("Limpieza completada, volviendo Idle")
            currentState = Idle
        }

        override fun toString(): String {
            return "Limpiando"
        }
    }
    data class Error(val message: String) : CoffeeMachineState()
}