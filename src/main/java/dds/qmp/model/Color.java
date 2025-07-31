package dds.qmp.model;

import dds.qmp.exceptions.ColorInvalidoException;

public class Color {
  int red;
  int green;
  int blue;

  Color(int red, int green, int blue) {
    validarRango(red);
    validarRango(green);
    validarRango(blue);

    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  private void validarRango(int valor) {
    if (valor < 0 || valor > 255) {
      throw new ColorInvalidoException("Color debe estar entre 0 y 255");
    }
  }
}
