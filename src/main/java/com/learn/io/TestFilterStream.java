package com.learn.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 针对最终的输入输出流进行功能添加时，可以使用Filter模式（装饰器模式）
 * 这样最终得到的始终是InputStream或OutputStream
 */
public class TestFilterStream {
    public static void learn() {
        countInputStream();
        zipInputStream();
        char a = '饕';
    }

    public static void countInputStream() {
        byte[] buffer = new byte[1024];
        try (CountInputStream inputStream = new CountInputStream(
                new FileInputStream("./src/main/resources/Test.txt"))) {
            inputStream.read(buffer);
            inputStream.read(buffer);
            inputStream.read(buffer);
            inputStream.read(buffer);
            System.out.println(inputStream.getReadByteCount());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipInputStream() {
        // 读取zip压缩包
        try (ZipInputStream zipInputStream = new ZipInputStream(
                new FileInputStream("./src/main/resources/test.zip"))) {
            ZipEntry entry = null;
            List<Byte> buffer = new ArrayList<>();
            while ((entry = zipInputStream.getNextEntry()) != null) {
                String name = entry.getName();
                System.out.println(name);
                if (name.equals("test/src/main/scala/test/model/User.scala")) {
                    int n;
                    while ((n = zipInputStream.read()) != -1) {
                        buffer.add((byte) n);
                    }
                }
            }
            byte[] data = new byte[buffer.size()];
            int i = 0;
            for (Byte b : buffer) {
                data[i++] = b.byteValue();
            }
            System.out.println(new String(data));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 写入zip包
//        try(ZipOutputStream zipOutputStream = new ZipOutputStream(
//                new FileOutputStream("./src/main/resources/test.zip"))) {
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

}

/**
 * 自己写一个拥有字节计数功能的FilterStream
 */
class CountInputStream extends FilterInputStream {
    private int count;

    protected CountInputStream(InputStream in) {
        super(in);
    }

    public int getReadByteCount() {
        return count;
    }

    @Override
    public int read() throws IOException {
        int n = in.read();
        if (n != -1) {
            count++;
        }
        return n;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int n = in.read(b, off, len);
        count += n;
        return n;
    }
}
