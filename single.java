public class single {
    int i;
    public static single a;
    static int counter;
    private single(int i){
        this.i = i;
        counter++;
    }

    public static single create(int i){
        if (counter < 2){
            return a = new single(i);
        }
        else {
            System.out.println("no obj created.");
            return a;
        }
    }
    void display(){
        System.out.println(a);
    }
}
