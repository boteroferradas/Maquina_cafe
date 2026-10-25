import org.example.Bebidas
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CoffeeMachineTest {

    @BeforeEach
    fun setUp() {
        // ¡Crucial! Como CoffeeMachine es un objeto global (object),
        // debemos limpiar sus valores antes de cada test para que no se afecten entre ellos.
        CoffeeMachine.currentState = CoffeeMachineState.Idle
        CoffeeMachine.monedero = 0.0
        CoffeeMachine.seleccion = Bebidas.NOBEBIDA
    }

    @Test
    fun `estado inicial debe ser Idle con monedero a cero`() {
        // Assert
        assertEquals(CoffeeMachineState.Idle, CoffeeMachine.currentState)
        assertEquals(0.0, CoffeeMachine.monedero)
        assertEquals(Bebidas.NOBEBIDA, CoffeeMachine.seleccion)
    }

    @Test
    fun `de Idle pasa a Standby si hay dinero en el monedero`() {
        // Arrange
        CoffeeMachine.monedero = 1.50

        // Act
        CoffeeMachine.gestorEstados() // Esto ejecuta el onenter del Idle

        // Assert
        assertEquals(CoffeeMachineState.Standby, CoffeeMachine.currentState)
    }

    @Test
    fun `de Standby pasa a CafeCorto si hay saldo suficiente y seleccion correcta`() {
        // Arrange
        CoffeeMachine.currentState = CoffeeMachineState.Standby
        CoffeeMachine.monedero = 2.00
        CoffeeMachine.seleccion = Bebidas.CORTO // Asumiendo que Bebidas.CORTO tiene un precio menor o igual a 2.00

        // Act
        CoffeeMachine.gestorEstados() // Ejecuta el onenter del Standby

        // Assert
        assertEquals(CoffeeMachineState.CafeCorto, CoffeeMachine.currentState)
    }

    @Test
    fun `de Standby vuelve a Idle si el saldo es insuficiente`() {
        // Arrange
        CoffeeMachine.currentState = CoffeeMachineState.Standby
        CoffeeMachine.monedero = 0.50
        CoffeeMachine.seleccion = Bebidas.CORTO // Asumiendo que el corto cuesta más de 0.50

        // Act
        CoffeeMachine.gestorEstados() // Ejecuta el onenter del Standby

        // Assert
        assertEquals(CoffeeMachineState.Idle, CoffeeMachine.currentState)
    }

    @Test
    fun `CafeListo calcula bien el cambio y limpia el monedero`() {
        // Arrange
        CoffeeMachine.currentState = CoffeeMachineState.CafeListo
        CoffeeMachine.monedero = 2.00
        CoffeeMachine.seleccion = Bebidas.CORTO // Digamos que cuesta 1.20

        // Act
        CoffeeMachine.gestorEstados() // Ejecuta CafeListo -> pasa a Limpiando y resetea

        // Assert
        assertEquals(0.0, CoffeeMachine.monedero)
        assertEquals(Bebidas.NOBEBIDA, CoffeeMachine.seleccion)
        assertEquals(CoffeeMachineState.Limpiando, CoffeeMachine.currentState)
    }
}