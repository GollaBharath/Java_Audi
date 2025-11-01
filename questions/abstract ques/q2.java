/*
Will this compile? Why must abstract methods be implemented?
*/
abstract class Shape {
    abstract void draw(); // no body
}
class Circle extends Shape {
    // forgot to override
    void draw() {
        System.out.println("Draws");
    }
}
public class q2 {
    public static void main(String[] args) {
        Shape s = new Circle();
        s.draw();
    }
}

