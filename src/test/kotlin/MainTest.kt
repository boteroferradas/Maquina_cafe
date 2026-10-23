import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.example.Bebidas

class CoffeeMachineTest {

    @BeforeEach
    fun resetMachine(){
        CoffeeMachine.monedero = 0.0
        CoffeeMachine.seleccion = Bebidas.NOBEBIDA
        CoffeeMachine.currentState = CoffeeMachineState.Idle
    }

    @Test
    fun `dado estado Idle sin dinero, permanece en Idle`() {
        CoffeeMachine.monedero = 0.0

        val nuevoEstado = CoffeeMachine.gestorEstados()

        assertEquals(CoffeeMachineState.Idle, nuevoEstado)
    }

    @Test
    fun `dado estado Idle con dinero, transiciona a Standby`() {
        CoffeeMachine.monedero = 1.0

        val nuevoEstado = CoffeeMachine.gestorEstados()

        assertEquals(CoffeeMachineState.Standby, nuevoEstado)
    }

    @Test
    fun `dado estado Standby con saldo suficiente para Cafe Corto, transiciona a CafeCorto`() {
        CoffeeMachine.currentState = CoffeeMachineState.Standby
        CoffeeMachine.monedero = 1.0
        CoffeeMachine.seleccion = Bebidas.CORTO

        val nuevoEstado = CoffeeMachine.gestorEstados()

        assertEquals(CoffeeMachineState.CafeCorto, nuevoEstado)
    }

    @Test
    fun `dado estado Standby sin saldo suficiente, no procesa la bebida`() {
        CoffeeMachine.currentState = CoffeeMachineState.Standby
        CoffeeMachine.monedero = 0.20
        CoffeeMachine.seleccion = Bebidas.CORTO

        val nuevoEstado = CoffeeMachine.gestorEstados()

        // Debería mantenerse en Standby o volver a Idle según tu lógica de diseño
        assertNotEquals(CoffeeMachineState.CafeCorto, nuevoEstado)
    }

    @Test
    fun `flujo completo desde CafeCorto hasta volver a Idle`() {
        // 1. Preparando café
        CoffeeMachine.currentState = CoffeeMachineState.CafeCorto
        CoffeeMachine.gestorEstados()
        assertEquals(CoffeeMachineState.CafeListo, CoffeeMachine.currentState)

        // 2. Café listo -> pasa a Limpiando
        CoffeeMachine.gestorEstados()
        assertEquals(CoffeeMachineState.Limpiando, CoffeeMachine.currentState)

        // 3. Limpiando -> regresa a Idle
        CoffeeMachine.gestorEstados()
        assertEquals(CoffeeMachineState.Idle, CoffeeMachine.currentState)
    }

////    @Test
////    fun `dado estado Error, no modifica el estado actual`() {
////        val estadoError = CoffeeMachineState.Error("Falta agua")
////        CoffeeMachine.currentState = estadoError
////
////        val nuevoEstado = CoffeeMachine.gestorEstados()
////
////        assertEquals(estadoError, nuevoEstado)
////    }
//}
}