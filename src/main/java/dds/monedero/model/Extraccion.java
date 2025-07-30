package dds.monedero.model;

import java.time.LocalDate;

/** JavaDoc. */
public class Extraccion extends TipoMovimiento {

  Extraccion() {
    this.nombre = "Extraccion";
  }

  public boolean coincide(String tipo, LocalDate fechaCheck, LocalDate fechaReal) {
    return fechaReal.equals(fechaCheck) && tipo.equals(nombre);
  }

  public double calcularValor(double saldo, double monto) {
    return saldo - monto;
  }
}
