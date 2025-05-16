package hust.soict.hedspi.garbage;

import java.util.Random;

public class ConcatenationInLoops {
    public static void main(String[] args) {
        Random r = new Random(123);

        // Doan nay dung toan tu + de noi chuoi
        long start = System.currentTimeMillis(); // lay thoi gian bat dau
        String s = "";
        for (int i = 0; i < 65536; i++) {
            s += r.nextInt(2); // noi chuoi bang toan tu +
        }
        System.out.println(System.currentTimeMillis() - start); // in ra thoi gian xu ly, khoang 4500ms

        // Doan nay su dung StringBuffer de toi uu hon
        r = new Random(123); // reset bo sinh so ngau nhien
        start = System.currentTimeMillis(); // lay thoi gian bat dau moi
        StringBuffer sb = new StringBuffer(); // su dung StringBuffer thay vi StringBuilder
        for (int i = 0; i < 65536; i++) {
            sb.append(r.nextInt(2)); // su dung phuong thuc append de noi chuoi
        }
        s = sb.toString(); // chuyen ve chuoi binh thuong
        System.out.println(System.currentTimeMillis() - start); // in ra thoi gian xu ly, thuong nhanh hon nhieu
    }
}

