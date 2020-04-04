package com.learn.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestReadResources {
    public static void learn() {
        getResources();
//        loadProperties();
    }

    public static void getResources() {
        /**
         * getResourceAsStream从classpath中直接读取文件，所有classpath中的资源文件都是以/开头
         */
        try (InputStream inputStream = TestReadResources.class
                .getResourceAsStream("./src/main/resources/default.properties")) {
            byte[] data = new byte[1024];
            while (inputStream.read(data) != -1) {
                System.out.println(new String(data));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(TestReadResources.class.getResourceAsStream("default.properties"));
            properties.forEach((key, value) -> {
                System.out.println(key+":"+value);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
