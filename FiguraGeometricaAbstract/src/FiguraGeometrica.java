// Clase abstracta FiguraGeometrica
abstract class FiguraGeometrica {
    // Método abstracto para calcular el área
    public abstract double calcularArea();

    // Método para comparar áreas de figuras geométricas
    public boolean mayorQue(FiguraGeometrica otraFigura) {
        return this.calcularArea() > otraFigura.calcularArea();
    }

    // Método para representar la figura geométrica como cadena de texto
    @Override
    public String toString() {
        return "Área: " + calcularArea();
    }
}

// Clase Rectangulo que hereda de FiguraGeometrica
class Rectangulo extends FiguraGeometrica {
    private double base;
    private double altura;

    public Rectangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return base * altura;
    }
}

// Clase Triangulo que hereda de FiguraGeometrica
class Triangulo extends FiguraGeometrica {
    private double base;
    private double altura;

    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return (base * altura) / 2;
    }
}

// Clase Circulo que hereda de FiguraGeometrica
class Circulo extends FiguraGeometrica {
    private double radio;

    public Circulo(double radio) {
        this.radio = radio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * radio * radio;
    }
}

