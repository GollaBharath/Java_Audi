public class Debug2 {
    public static void main(String[] args) {
        try {
            int[] arr = new int[5];
            arr[10] = 100;
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception caught");
        }
    }
}
