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
}
