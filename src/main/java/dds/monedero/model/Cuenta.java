package dds.monedero.model;

import dds.monedero.exceptions.DepositosMaximosMenorException;
import dds.monedero.exceptions.LimiteDiarioMenorException;
import dds.monedero.exceptions.MaximaCantidadDepositosException;
import dds.monedero.exceptions.MaximoExtraccionDiarioException;
import dds.monedero.exceptions.MontoNegativoException;
import dds.monedero.exceptions.SaldoMenorException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** JavaDoc. */
public class Cuenta {

  private double saldo = 0;
  private double extraccionMaximaDiaria;
  private Integer depositosMaximosDiarios;
  private List<Movimiento> movimientos = new ArrayList<>();

  public Cuenta(Integer depositosMax, double extraccionMaximaDiaria) throws DepositosMaximosMenorException, LimiteDiarioMenorException{
    if (depositosMax <= 0) {
      throw new DepositosMaximosMenorException(
          "El valor maximo de deposito debe ser mayor que 0: " + depositosMax);
    } else if (extraccionMaximaDiaria <= 0) {
      throw new LimiteDiarioMenorException(
          "El valor del limite diario debe ser mayor que 0: " + extraccionMaximaDiaria);
    }

    this.depositosMaximosDiarios = depositosMax;
    this.extraccionMaximaDiaria = extraccionMaximaDiaria;
  }

  public Cuenta(double montoInicial, Integer depositosMax) {
    saldo = montoInicial;
    this.depositosMaximosDiarios = depositosMax;
  }

  private void verificarSaldo(double cuanto) throws MontoNegativoException {
    if (cuanto <= 0) {
      throw new MontoNegativoException(cuanto + ": el monto a ingresar debe ser un valor positivo");
    }
  }

  private void verificarDepositos(double cuanto) throws MaximaCantidadDepositosException {
    if (obtenerCantidadDeDepositos(LocalDate.now()) >= depositosMaximosDiarios) {
      throw new MaximaCantidadDepositosException("Ya excedio los "
          + depositosMaximosDiarios + " depositos diarios");
    }
  }

  private void verificarExtraccionSaldo(double cuanto) throws SaldoMenorException {
    if (getSaldo() - cuanto < 0) {
      throw new SaldoMenorException("No puede sacar mas de " + getSaldo() + " $");
    }
  }

  private void verificarExtraccionDiario(double cuanto, double limite) throws MaximoExtraccionDiarioException {
    if (cuanto > limite) {
      throw new MaximoExtraccionDiarioException(
          "No puede extraer mas de $ " + 1000 + " diarios, " + "límite: " + limite);
    }
  }

  /** JavaDoc. */
  public void poner(double cuanto) {

    verificarSaldo(cuanto);
    verificarDepositos(cuanto);

    TipoMovimiento tipoM = new Deposito();

    setSaldo(tipoM.calcularValor(getSaldo(), cuanto));
    agregarMovimiento(LocalDate.now(), cuanto, tipoM);
  }

  public long obtenerCantidadDeDepositos(LocalDate fechaCheck) {
    return getMovimientos().stream()
        .filter(movimiento -> movimiento.getTipo()
            .coincide("Deposito", fechaCheck, movimiento.getFecha()))
        .count();
  }

  /** JavaDoc. */
  public void sacar(double cuanto) {

    var montoExtraidoHoy = getMontoExtraidoA(LocalDate.now());
    var limite = extraccionMaximaDiaria - montoExtraidoHoy;

    verificarSaldo(cuanto);

    verificarExtraccionSaldo(cuanto);

    verificarExtraccionDiario(cuanto, limite);

    TipoMovimiento tipoM = new Extraccion();
    setSaldo(tipoM.calcularValor(getSaldo(), cuanto));
    agregarMovimiento(LocalDate.now(), cuanto, tipoM);
  }

  public void agregarMovimiento(LocalDate fecha, double cuanto, TipoMovimiento tipoM) {
    var movimiento = new Movimiento(fecha, cuanto, tipoM);
    movimientos.add(movimiento);
  }

  /** JavaDoc. */
  public double getMontoExtraidoA(LocalDate fechaCheck) {
    return getMovimientos().stream()
        .filter(movimiento -> !movimiento.getTipo()
            .coincide("Extraccion", fechaCheck, movimiento.getFecha()))
        .mapToDouble(Movimiento::getMonto)
        .sum();
  }

  public List<Movimiento> getMovimientos() {
    return movimientos;
  }

  public void setMovimientos(List<Movimiento> movimientos) {
    this.movimientos = movimientos;
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

  public Integer getDepositosMaximosDiarios() {
    return depositosMaximosDiarios;
  }
}
