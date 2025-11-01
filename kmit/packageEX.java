package kmit;
public class packageEX {
    public int a;
    public packageEX(int a) {
        this.a = a;
    }
    public void display(){
        System.out.println(a);
    }
    static void m1(){
        System.out.println("static method");
    }
    public static void main(String[] args){
        packageEX.m1();
    }
}