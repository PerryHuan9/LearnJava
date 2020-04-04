package com.learn.io;

import java.io.*;

public class TestOutputStream {
    public static void learn() {
        fileOutputStream();
        byteArrayOutputStream();
    }

    /**
     * 写入文件中
     */
    public static void fileOutputStream() {
        try(OutputStream outputStream = new FileOutputStream("./src/main/resources/Test.txt",true)) {
            outputStream.write("我是FileOutputStream写入的内容\n".getBytes());
            outputStream.write("我是FileOutputStream写入的内容\n".getBytes());
            outputStream.write("我是FileOutputStream写入的内容\n".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 写入ByteArray中
     */
    public static void byteArrayOutputStream() {
        byte[] bytes = {};
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            outputStream.write("天下无不散的宴席\n".getBytes());
            outputStream.write("人生得意须尽欢\n".getBytes());
            bytes = outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(new String(bytes));
    }

}
