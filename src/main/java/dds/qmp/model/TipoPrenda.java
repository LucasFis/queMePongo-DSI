package dds.qmp.model;

public enum TipoPrenda {

  ZAPATILLA(Categoria.CALZADO),
  PANTALON(Categoria.PARTE_INFERIOR),
  REMERA(Categoria.PARTE_SUPERIOR),
  ANTEOJOS(Categoria.ACCESORIO);

  final Categoria categoria;

  TipoPrenda(Categoria categoria) {
    this.categoria = categoria;
  }

  public Categoria getCategoria() {
    return categoria;
  }
}
