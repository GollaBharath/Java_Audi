/*
What will be printed? Why can sides only be set once?

*/


abstract class Shape {
    final int sides;
    { sides = 4; }   // initializer
    Shape() {
        System.out.println("Shape created with sides = " + sides);
    }
    abstract void draw();
}
class Square extends Shape {
    void draw() { System.out.println("Drawing Square"); }
}
public class q10 {
    public static void main(String[] args) {
        Shape s = new Square();
        s.draw();
    }
}



