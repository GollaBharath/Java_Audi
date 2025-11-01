/*
 Why is it useful for readability to mark MAX_PAGES as final?

 It's better to assign it there because creating a constructor just for one variable is not recommended.

*/

class Report {
    final int MAX_PAGES = 50;
    void printReport(int pages) {
        if (pages > MAX_PAGES)
            System.out.println("Too many pages");
        else
            System.out.println("Printing " + pages + " pages");
    }
}
public class test10 {
    public static void main(String[] args) {
        Report r = new Report();
        r.printReport(30);
        // r.MAX_PAGES = 100; // try changing - // ! Doesn't work
    }
}

