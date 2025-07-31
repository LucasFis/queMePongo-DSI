package dds.qmp.model;

import dds.qmp.exceptions.UniformeInvalidoException;
import dds.qmp.exceptions.ColorInvalidoException;
import dds.qmp.exceptions.PrendaInvalidaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QMPTest {
  Prenda jean;
  Prenda camisa;
  Prenda anteojos;
  Prenda zapatilla;

  @BeforeEach
  void setUp() {

    zapatilla = crearPrenda(TipoPrenda.ZAPATILLA, Material.CUERO, null, new Color(255, 255, 255), null);
    jean = crearPrenda(TipoPrenda.PANTALON, Material.ALGODON, null, new Color(255, 255, 255), null);
    camisa = crearPrenda(TipoPrenda.REMERA, Material.ALGODON, null, new Color(255, 255, 255), new Color(0,0,0));
    anteojos = crearPrenda( TipoPrenda.ANTEOJOS, Material.PLASTICO, null, new Color(255, 255, 255), null);
  }

  @Test
  public void noSePuedeCrearUnaPrendaSinLosPrimerosTresElementos() {
    assertThrows(PrendaInvalidaException.class, () -> {
      PrendaBuilder builder = new PrendaBuilder();
      builder.asignarMaterial(Material.CUERO);
      builder.asignarColorPrimario( new Color(255, 255, 255));
      builder.crearPrenda();
    });
    assertThrows(RuntimeException.class, () -> {
      PrendaBuilder builder = new PrendaBuilder();
      builder.asignarTipoPrenda(TipoPrenda.PANTALON);
      builder.asignarColorPrimario( new Color(255, 255, 255));
      builder.crearPrenda();
    });
    assertThrows(RuntimeException.class, () -> {
      PrendaBuilder builder = new PrendaBuilder();
      builder.asignarTipoPrenda(TipoPrenda.PANTALON);
      builder.asignarTipoPrenda(TipoPrenda.PANTALON);
      builder.crearPrenda();
    });
  }

  @Test
  public void sePuedeCrearUnUniforme() {

    assertDoesNotThrow(() -> {new Uniforme(camisa, jean, zapatilla);});
  }

  @Test
  public void crearUnUniformeInvalidoArrojaExcepcion() {

    assertThrows(UniformeInvalidoException.class, () -> {
      new Uniforme(zapatilla, jean, camisa);
    });
  }

  @Test
  public void crearUnColorInvalidoArrojaExcepcion() {
    assertThrows(ColorInvalidoException.class, () -> {
      new Color(256,256,256);
    });

    assertThrows(ColorInvalidoException.class, () -> {
      new Color(256,2,3);
    });

    assertThrows(ColorInvalidoException.class, () -> {
      new Color(-200,0,0);
    });
  }

  public Prenda crearPrenda(TipoPrenda tipoPrenda, Material material, Trama trama, Color colorPrimario, Color colorSecundario) {
    PrendaBuilder builder = new PrendaBuilder();
    builder.asignarTipoPrenda(tipoPrenda);
    builder.asignarMaterial(material);
    builder.asignarTrama(trama);
    builder.asignarColorPrimario(colorPrimario);
    builder.asignarColorSecundario(colorSecundario);
    return builder.crearPrenda();
  }
}