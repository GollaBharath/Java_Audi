/*

Will it compile? Where should we initialize PORT?

we should do it while declaring it, because static variables get created when class is loaded into memory.
*/

class Config {
    static final int PORT = 8080; //only works here.
    Config() {
        // PORT = 8080; // attempt in constructor
    }
}
public class test6 {
    public static void main(String[] args) {
        System.out.println(Config.PORT);
    }
}

