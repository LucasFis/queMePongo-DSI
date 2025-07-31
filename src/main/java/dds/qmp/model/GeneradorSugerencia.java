package dds.qmp.model;

import java.util.ArrayList;
import java.util.List;

public abstract class GeneradorSugerencia {

  public abstract List<Uniforme> sugerir(List<Prenda> guardarropa, int edad);

  public List<Prenda> buscarCombinaciones(List<Prenda> prendas, Prenda prenda) {
    List<Prenda> combinaciones = new ArrayList<>();

    for (Prenda prendaAux : prendas) {
      if (prenda.combinaCon(prendaAux)) {
        combinaciones.add(prendaAux);
      }
    }

    return combinaciones;
  }

  public List<Uniforme> armarUniformes(List<Prenda> combinaciones, Prenda prenda) {
    combinaciones = combinaciones.stream().filter(
        p -> p.getCategoria() != prenda.getCategoria()).toList();

    List<Uniforme> uniformes = new ArrayList<>();

    List<Prenda> partesSuperiores = separarPor(combinaciones, Categoria.PARTE_SUPERIOR);
    List<Prenda> partesInferiores = separarPor(combinaciones, Categoria.PARTE_INFERIOR);
    List<Prenda> calzados = separarPor(combinaciones, Categoria.CALZADO);



    for (Prenda prenda1 : partesSuperiores) {
      for (Prenda prenda2 : partesInferiores) {
        for (Prenda prenda3 : calzados) {
          uniformes.add(new Uniforme(prenda1, prenda2, prenda3));
        }
      }
    }

    return uniformes;
  }

  protected List<Prenda> separarPor(List<Prenda> prendas, Categoria categoria) {
    return prendas.stream().filter(p -> p.getCategoria() == categoria).toList();
  }

  protected Boolean noTieneInformal(Uniforme uniforme) {
    return uniforme.getCalzado().getFormalidad() != Formalidad.INFORMAL
        && uniforme.getParteSuperior().getFormalidad() != Formalidad.INFORMAL
        && uniforme.getParteInferior().getFormalidad() != Formalidad.INFORMAL;
  }
}
