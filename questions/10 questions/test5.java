/*
Will this compile? If not, how should we fix it?

it wont compile. Assign the value of 'r' to 'rollNo' to fix it.

*/
class Student {
    final int rollNo;
    Student(int r) {
        // rollNo not initialized
        rollNo = r;
    }
    void show() {
        System.out.println("RollNo = " + rollNo);
    }
}
public class test5 {
    public static void main(String[] args) {
        Student s = new Student(101);
        s.show();
    }
}
