package dds.monedero.model;

import dds.monedero.exceptions.DepositosMaximosMenorException;
import dds.monedero.exceptions.LimiteDiarioMenorException;
import dds.monedero.exceptions.MaximaCantidadDepositosException;
import dds.monedero.exceptions.MaximoExtraccionDiarioException;
import dds.monedero.exceptions.MontoNegativoException;
import dds.monedero.exceptions.SaldoMenorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MonederoTest {
  private Cuenta cuenta;

  @BeforeEach
  void init() {
    cuenta = new Cuenta(3, 1000.0);
  }

  @Test
  @DisplayName("Es posible poner $1500 en una cuenta vacía")
  void Poner() {
    cuenta.poner(1500);
  }

  @Test
  @DisplayName("No es posible poner montos negativos")
  void PonerMontoNegativo() {
    assertThrows(MontoNegativoException.class, () -> cuenta.poner(-1500));
  }

  @Test
  @DisplayName("Es posible realizar múltiples depósitos consecutivos")
  void TresDepositos() {
    for (int i = 0; i <= cuenta.getDepositosMaximosDiarios() - 1 ; i++) {
      cuenta.poner(1 + 500 * i);
    }
  }

  @Test
  @DisplayName("No es posible superar la máxima cantidad de depositos diarios")
  void MasDeTresDepositos() {
    assertThrows(MaximaCantidadDepositosException.class, () -> {

      for (int i = 0; i <= cuenta.getDepositosMaximosDiarios(); i++) {
        cuenta.poner(1 + 500 * i);
      }
    });
  }

  @Test
  @DisplayName("No es posible extraer más que el saldo disponible")
  void ExtraerMasQueElSaldo() {
    assertThrows(SaldoMenorException.class, () -> {
      cuenta.setSaldo(90);
      cuenta.sacar(1001);
    });
  }

  @Test
  @DisplayName("No es posible extraer más que el límite diario")
  void ExtraerMasDe1000() {
    assertThrows(MaximoExtraccionDiarioException.class, () -> {
      cuenta.setSaldo(5000);
      cuenta.sacar(1001);
    });
  }

  @Test
  @DisplayName("No es posible extraer un monto negativo")
  void ExtraerMontoNegativo() {
    assertThrows(MontoNegativoException.class, () -> cuenta.sacar(-500));
  }

  @Test
  @DisplayName("Los movimientos afectan el estado interno de la cuenta")
  void LosMovimientosAfectanALaLista() {
    cuenta.poner(500);
    cuenta.sacar(400);
    assertEquals(2, cuenta.getMovimientos().toArray().length);
  }

  @Test
  @DisplayName("Los movimientos corresponden a la accion hecha en la cuenta")
  void TipoDeMovimientoCorresponde() {
    cuenta.poner(500);
    assertEquals("Deposito", cuenta.getMovimientos().get(0).getTipo().nombre);
  }

  @Test
  @DisplayName("Los depositos menor o iguales a 0 arrojan una excepcion")
  void DepositoMaximoMenor() {
    assertThrows(DepositosMaximosMenorException.class, () -> {
      new Cuenta(-2, 1000.0);
    });
  }

  @Test
  @DisplayName("Los Limites diarios menor o iguales a 0 arrojan una excepcion")
  void LimiteMaximoMenor() {
    assertThrows(LimiteDiarioMenorException.class, () -> {
      new Cuenta(3, -10.0);
    });
  }
}