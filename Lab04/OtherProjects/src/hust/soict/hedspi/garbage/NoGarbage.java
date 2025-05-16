package hust.soict.hedspi.garbage;

import java.nio.file.Files;
import java.nio.file.Paths;

public class NoGarbage {
    public static void main(String[] args) throws Exception {
        String filename = "test.exe"; // duong dan toi file
        byte[] inputBytes = { 0 };
        long startTime, endTime;

        inputBytes = Files.readAllBytes(Paths.get(filename));
        startTime = System.currentTimeMillis();

        StringBuffer outputBuffer = new StringBuffer(); // su dung StringBuffer
        for (byte b : inputBytes) {
            outputBuffer.append((char) b); // dung append de noi chuoi
        }

        String outputString = outputBuffer.toString(); // chuyen ve chuoi
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime); // in thoi gian xu ly
    }
}
