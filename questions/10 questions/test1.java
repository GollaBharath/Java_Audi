/*

What will happen? Compilation error or runtime output? How do we fix it?

compilation error, because we can not change a final variable's value.
*/


class test1 {
    final int x = 10;
    public static void main(String[] args) {
        test1 d = new test1();
        // d.x = 20; // try to change
        System.out.println(d.x);
    }
}

