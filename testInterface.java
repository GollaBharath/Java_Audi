public class testInterface {
    public static void main(String[] args) {
        chessPlayer Mee = new chessPlayer();
        Mee.play();
        Mee.eat();
        Mee.sleep();
        // Mee.repeat(); XD
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

interface ITest extends IPlayer,IPerson { // inheritance works with interfaces, but same would have failed with classes
    void works(); 
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