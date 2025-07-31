package dds.qmp.model;

import dds.qmp.exceptions.PrendaInvalidaException;
import static java.util.Objects.requireNonNull;

public class PrendaBuilder {
  public Prenda prenda;

  public PrendaBuilder() {
    this.prenda = new Prenda();
  }

  public void asignarTipoPrenda(TipoPrenda tipoPrenda) {
    this.prenda.tipoPrenda = requireNonNull(tipoPrenda, "Una prenda debe tener tipo de prenda");
      }

  public void asignarMaterial(Material material) {
    this.prenda.material = requireNonNull(material, "Una prenda debe tener material");
  }

  public void asignarTrama(Trama trama) {
    this.prenda.trama = trama == null ? Trama.LISA : trama;
  }

  public void asignarColorPrimario(Color colorPrimario) {
    this.prenda.colorPrimario = requireNonNull(
        colorPrimario, "Una prenda debe tener color primario");
  }

  public void asignarColorSecundario(Color colorSecundario) {
    this.prenda.colorSecundario = colorSecundario;
  }

  public Prenda crearPrenda() {
    if (this.prenda.tipoPrenda == null || this.prenda.material == null
        || this.prenda.trama == null || this.prenda.colorPrimario == null) {
      throw new PrendaInvalidaException("Esta prenda no fue terminada");
    }
    return this.prenda;
  }
}
