/*

What happens when you try to reassign x?

it gives a compilation error
*/

class Calculator {
    int add(final int x, final int y) {
        // x = x + 1; // try to reassign
        return x + y;
    }
}
public class test7 {
    public static void main(String[] args) {
        Calculator c = new Calculator();
        System.out.println(c.add(3, 4));
    }
}
