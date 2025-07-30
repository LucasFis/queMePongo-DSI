package dds.monedero.model;

import java.time.LocalDate;

/** JavaDoc. */
public abstract class TipoMovimiento {

  String nombre = null;

  public String getNombre() {
    return nombre;
  }

  public abstract boolean coincide(String tipo, LocalDate fechaCheck, LocalDate fechaReal);

  public abstract double calcularValor(double saldo, double monto);
}
