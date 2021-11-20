package mian.java.base.io;

import java.io.InputStream;

public class InputStreamDemo {
    public static void main(String[] args) {
        System.out.println("联系流");
        InputStream resourceAsStream = InputStreamDemo.class.getClassLoader().getResourceAsStream("resource/abc.txt");
        System.out.println(resourceAsStream.hashCode());
    }
}
