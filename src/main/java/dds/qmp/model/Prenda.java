package dds.qmp.model;

import static java.util.Objects.requireNonNull;

public class Prenda {
  Material material;
  TipoPrenda tipoPrenda;
  Trama trama;
  Color colorPrimario;
  Color colorSecundario;

  public Categoria getCategoria() {
    return this.tipoPrenda.getCategoria();
  }
}
