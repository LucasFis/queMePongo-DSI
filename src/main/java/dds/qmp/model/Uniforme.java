package dds.qmp.model;

import dds.qmp.exceptions.UniformeInvalidoException;

public class Uniforme {
  Prenda parteSuperior;
  Prenda parteInferior;
  Prenda calzado;

  Uniforme(Prenda parteSuperior, Prenda parteInferior, Prenda calzado)
      throws UniformeInvalidoException {
    if (parteSuperior.getCategoria() != Categoria.PARTE_SUPERIOR
        || parteInferior.getCategoria() != Categoria.PARTE_INFERIOR
        || calzado.getCategoria() != Categoria.CALZADO) {
      throw new UniformeInvalidoException("El atuendo seleccionado es invalido en sus atributos");
    }
    this.parteSuperior = parteSuperior;
    this.parteInferior = parteInferior;
    this.calzado = calzado;
  }

  public Prenda getParteSuperior() {
    return parteSuperior;
  }

  public void setParteSuperior(Prenda parteSuperior) {
    this.parteSuperior = parteSuperior;
  }

  public Prenda getParteInferior() {
    return parteInferior;
  }

  public void setParteInferior(Prenda parteInferior) {
    this.parteInferior = parteInferior;
  }

  public Prenda getCalzado() {
    return calzado;
  }

  public void setCalzado(Prenda calzado) {
    this.calzado = calzado;
  }
}
