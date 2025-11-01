/*

Will it print and then update? Or fail?

It will not compile at all because we are trying to assign a value to a final variable.

*/
class test2 {
    final double PI = 3.14159;
    public static void main(String[] args) {
        test2 c = new test2();
        System.out.println(c.PI);
        // c.PI = 22.7; // modify?
    }
}
