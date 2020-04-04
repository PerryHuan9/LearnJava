package com.learn.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * InputStream 是一个抽象类，它是所有输入流的超类。
 */
public class TestInputStream {
    public static void learn() {
        fileInputStream();
        byteArrayInputStream();
    }

    public static void log(Object o) {
        System.out.println(o);
    }

    /**
     * int read() 读取输入流的下一个字节的int表示
     * int read(byte[] b)：读取若干字节并填充到byte[]数组，返回读取的字节数
     * int read(byte[] b, int off, int len)：指定byte[]数组的偏移量和最大填充数
     */
    public static void fileInputStream() {
        try (InputStream inputStream = new FileInputStream("./src/main/resources/Test.txt")) {
            int len;
            byte[] buffer = new byte[1024];
            while ((len = inputStream.read(buffer)) != -1) {
                log("读取长度：" + len);
                log(new String(buffer));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void byteArrayInputStream() {
        byte[] data = { 72, 101, 108, 108, 111, 33};
        try(InputStream inputStream = new ByteArrayInputStream(data)) {
            int b ;
            while ((b= inputStream.read())!=-1) {
                log((char)b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
