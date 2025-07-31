package dds.qmp.model;

import static java.util.Objects.requireNonNull;

import dds.qmp.exceptions.PrendaInvalidaException;

public class PrendaBuilder {
  public Prenda prenda;

  public PrendaBuilder() {
    this.prenda = new Prenda();
  }

  public void asignarTipoPrenda(TipoPrenda tipoPrenda) {
    this.prenda.setTipoPrenda(requireNonNull(tipoPrenda, "Una prenda debe tener tipo de prenda"));
  }

  public void asignarMaterial(Material material) {
    this.prenda.setMaterial(requireNonNull(material, "Una prenda debe tener material"));
  }

  public void asignarTrama(Trama trama) {
    this.prenda.setTrama(trama == null ? Trama.LISA : trama);
  }

  public void asignarFormalidad(Formalidad formalidad) {
    this.prenda.setFormalidad(requireNonNull(formalidad, "Una prenda debe tener una formalidad"));
  }

  public void asignarColorPrimario(Color colorPrimario) {
    this.prenda.setColorPrimario(
        requireNonNull(colorPrimario, "Una prenda debe tener color primario"));
  }

  public void asignarColorSecundario(Color colorSecundario) {
    this.prenda.setColorSecundario(colorSecundario);
  }

  public Prenda crearPrenda() {
    if (this.prenda.getTipoPrenda() == null || this.prenda.getMaterial() == null
        || this.prenda.getTrama() == null || this.prenda.getColorPrimario() == null
        || this.prenda.getFormalidad() == null) {
      throw new PrendaInvalidaException("Esta prenda no fue terminada");
    }
    return this.prenda;
  }
}
