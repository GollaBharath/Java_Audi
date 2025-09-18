@FunctionalInterface
interface kmit {
    void m();
}

public class testFI {
    public static void main(String[] args){
        kmit i = ()->{
            //function
            System.out.println("Lambda function");
        };
        i.m(); // prints Lambda function
    }
}