package dds.qmp.model;

import static java.util.Objects.requireNonNull;

public class Prenda {
  Material material;
  TipoPrenda tipoPrenda;
  Trama trama;
  Formalidad formalidad;
  Color colorPrimario;
  Color colorSecundario;

  public boolean combinaCon(Prenda prenda) {
    return this.getMaterial() == prenda.getMaterial()
        && this.colorPrimario == prenda.getColorPrimario();
  }

  public Categoria getCategoria() {
    return this.tipoPrenda.getCategoria();
  }

  public Material getMaterial() {
    return this.material;
  }

  public TipoPrenda getTipoPrenda() {
    return this.tipoPrenda;
  }

  public Trama getTrama() {
    return this.trama;
  }

  public Formalidad getFormalidad() {
    return this.formalidad;
  }

  public Color getColorPrimario() {
    return this.colorPrimario;
  }

  public Color getColorSecundario() {
    return this.colorSecundario;
  }

  public void setMaterial(Material material) {
    this.material = material;
  }

  public void setTipoPrenda(TipoPrenda tipoPrenda) {
    this.tipoPrenda = tipoPrenda;
  }

  public void setTrama(Trama trama) {
    this.trama = trama;
  }

  public void setFormalidad(Formalidad formalidad) {
    this.formalidad = formalidad;
  }

  public void setColorPrimario(Color colorPrimario) {
    this.colorPrimario = colorPrimario;
  }

  public void setColorSecundario(Color colorSecundario) {
    this.colorSecundario = colorSecundario;
  }
}
