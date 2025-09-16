public class testInterface {
    public static void main(String[] args) {
        
    }
}

interface IPlayer {
    void win();
    void lose();
    void play();
}

interface IPerson {
    void eat();
    void sleep();
}

class chessPlayer implements IPerson, IPlayer {
    public void win() {
        System.out.println("Won");
    }
    public void lose() {
        System.out.println("Won");
    }
    public void play() {
        System.out.println("Won");
    }
    public void eat() {
        System.out.println("Won");
    }
    public void sleep() {
        System.out.println("Won");
    }
}