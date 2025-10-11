import java.io.*;

public class files {
    public static void main(String[] args) {
        File f = new File("/home/dead/Downloads/ubuntu-24.04.3-desktop-amd64.iso");
        System.out.println(f.isDirectory());
        if (!f.isDirectory()) {
            System.out.println((f.length() / 1000000.0) / 1024.0);
        }
    }
}
