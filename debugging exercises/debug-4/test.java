public class test {
    public static void main(String[] args) {
        try {
            int x = 10 / 0;
        }
        catch (Exception e) {
            System.out.println("General Exception");
        }
        catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception");
        }
    }
}
