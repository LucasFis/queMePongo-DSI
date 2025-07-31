package dds.qmp.model;

public abstract class Sastre {

  public Uniforme fabricarUniforme() {

    return new Uniforme(fabricarParteSuperior(),
        fabricarParteInferior(),
        fabricarCalzado());
  }

  public abstract Prenda fabricarParteSuperior();

  public abstract Prenda fabricarParteInferior();

  public abstract Prenda fabricarCalzado();
}
