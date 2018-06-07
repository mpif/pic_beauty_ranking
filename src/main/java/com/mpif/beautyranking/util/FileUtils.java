package com.mpif.beautyranking.util;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64EncoderStream;
import org.apache.commons.codec.binary.StringUtils;

import java.io.*;
import java.util.Scanner;

/**
 * @author: mpif
 * @date: 2018-06-07 15:17
 */
public class FileUtils {

    public static void main(String[] args) {
        String filePath = "/githubForSourcetree/pic_beauty_ranking/src/main/resources/base64Str.txt";
        String fileStr = FileUtils.fileToStr(filePath);
        System.out.println("fileStr:");
        System.out.println(fileStr);
    }

    // read string from file, for debug
    public static String fileToStr(String path) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = null;
        try {
            sc = new Scanner(new File(path));
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
            }
            sc.close();
            sc = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                IOUtils.close(sc);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String fileToBase64Str(String filePath) {

        if(filePath == null || filePath.trim().length() == 0) {
            throw new IllegalArgumentException("filePath can't be empty.");
        }

        File file = new File(filePath);
        if(!file.exists()) {
            throw new IllegalArgumentException("[" + filePath + "] not exists");
        }

        StringBuilder sb = new StringBuilder();

        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        String base64Str = "";
        OutputStream os = null;
        byte[] data = new byte[40960];

        try {


            os = new ByteArrayOutputStream();

            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(new BASE64EncoderStream(os));

            int len = 0;
            while ((len = bis.read(data)) != -1) {
                bos.write(data, 0, len);
            }

            sb.append(os.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                IOUtils.close(bis, bos);
            } catch (IOException ex3) {
                ex3.printStackTrace();
            }
        }
        base64Str = sb.toString();
        System.out.println("文件的base64字符串为:");
        base64Str = base64Str.replaceAll("\r\n", "");
        System.out.println(base64Str);

        return base64Str;
    }

}
