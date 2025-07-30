package dds.monedero.model;

import java.time.LocalDate;

/** JavaDoc. */
public class Deposito extends TipoMovimiento {

  Deposito() {
    this.nombre = "Deposito";
  }

  public boolean coincide(String tipo, LocalDate fechaCheck, LocalDate fechaReal) {
    return fechaReal.equals(fechaCheck) && tipo.equals(nombre);
  }

  public double calcularValor(double saldo, double monto) {
    return saldo + monto;
  }
}
