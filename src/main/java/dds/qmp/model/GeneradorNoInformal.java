package dds.qmp.model;

import java.util.ArrayList;
import java.util.List;

public class GeneradorNoInformal extends GeneradorSugerencia {

  //int edad, posible fachada
  public List<Uniforme> sugerir(List<Prenda> guardarropa, int edad) {
    List<Uniforme> sugerencia = new ArrayList<>();

    guardarropa = guardarropa.stream().filter(p -> p.getCategoria() != Categoria.ACCESORIO).toList();

    for (Prenda prenda : guardarropa) {
      List<Prenda> combinaciones = buscarCombinaciones(guardarropa, prenda);
      sugerencia = armarUniformes(combinaciones, prenda);
    }

    return edad < 55 ? sugerencia : filtrarInformal(sugerencia);
  }

  private List<Uniforme> filtrarInformal(List<Uniforme> sugerenciaOriginal) {
    return sugerenciaOriginal.stream().filter(this::noTieneInformal).toList();
  }
}
