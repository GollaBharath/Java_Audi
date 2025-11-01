/*
Will the finally block execute? Why or why not?

*/
public class test {
    public static void main(String[] args) {
        try {
            System.out.println("Inside try");
            System.exit(0);
        } finally {
            System.out.println("Finally block executed");
        }
    }
}
