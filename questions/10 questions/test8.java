/*
Can we make the Book class truly immutable? What else should we add? 

We should add final before class Book
*/
final class Book {
    final String title = "Java";
}
public class test8 {
    public static void main(String[] args) {
        Book b = new Book();
        //b.title = "Python"; // try to change
        System.out.println(b.title);
    }
}
