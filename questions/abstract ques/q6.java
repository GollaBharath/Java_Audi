/*
Can an abstract class have only concrete methods? Can we instantiate it?
*/

class Utility {
    void help() {
        System.out.println("Helping...");
    }
}
public class q6 {
    public static void main(String[] args) {
        Utility u = new Utility(); // try object creation
        u.help();
    }
}


