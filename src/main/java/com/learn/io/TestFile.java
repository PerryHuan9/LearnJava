package com.learn.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class TestFile {

    public static void learn() {
        basic();
        create();
        list();
        path();
    }

    public static void log(Object o) {
        System.out.println(o);
    }

    public static void basic() {
        File file = new File("./src/main/resources/logback.xml");
        log(file.getPath());
        log(file.getAbsolutePath());
        try {
            log(file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        log(file.isFile());
        File dir = new File("src/main/resources");
        log(dir.isDirectory());
        log(dir.isFile());
        log("canRead():" + file.canRead());
        log("canWrite():" + file.canWrite());
        log("canExecute():" + file.canExecute());
        log("length():" + file.length());
    }

    public static void create() {
        File file = new File("/Users/huangyilin/Desktop/HelloWorld.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            file.delete();
        }
        // 创建临时文件
        try {
            File tempFile = File.createTempFile("tmp-", ".txt");
            tempFile.deleteOnExit(); // JVM退出时删除
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 创建目录
        File dir = new File("/Users/huangyilin/Desktop/HelloWorld");
        if (!dir.exists()) {
            dir.mkdir();
        } else {
            dir.delete();
        }
        // 创建目录时不存在的父目录也一起创建了
        File dirs = new File("/Users/huangyilin/Desktop/First/Second");
        if (!dirs.exists()) {
            dirs.mkdirs();
        } else {
            dirs.delete();
        }
        File dir1 = new File("/Users/huangyilin/Desktop/First");
        log(Arrays.toString(dir1.list()));
        dir1.delete();
    }

    public static void list() {
        File desktop = new File("/Users/huangyilin/Desktop");
        log(Arrays.toString(desktop.list()));
        File[] files = desktop.listFiles(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory();
            }
        });
        log("遍历Desktop");
        Arrays.stream(files).map((file) -> file.getName()).forEach((name) -> {
            log(name);
        });
    }

    public static void path() {
        Path path = Paths.get(".", "project", "study");
        log(path);
        Path path1 = path.toAbsolutePath();
        log(path1);
        Path path2 = path1.normalize(); // 转换为规范路径
        log(path2);
        File file = path2.toFile();
        log(file);
    }
}
