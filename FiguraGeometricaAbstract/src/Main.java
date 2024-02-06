public class Main {
    public static void main(String[] args) {
        FiguraGeometrica rectangulo = new Rectangulo(5, 10);
        FiguraGeometrica triangulo = new Triangulo(3, 6);
        FiguraGeometrica circulo = new Circulo(4);

        System.out.println("Área del rectángulo: " + rectangulo);
        System.out.println("Área del triángulo: " + triangulo);
        System.out.println("Área del círculo: " + circulo);

        if (rectangulo.mayorQue(triangulo)) {
            System.out.println("El rectángulo tiene un área mayor que el triángulo.");
        } else {
            System.out.println("El triángulo tiene un área mayor que el rectángulo.");
        }
    }
}