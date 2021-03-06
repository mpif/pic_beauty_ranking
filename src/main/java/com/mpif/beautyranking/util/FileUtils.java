package com.mpif.beautyranking.util;

//import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64DecoderStream;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64EncoderStream;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;


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

    public static byte[] getFileBytes(String filePath) throws IOException {
        File file = new File(filePath);
        if(!file.exists()) {
            throw new FileNotFoundException("[" + filePath + "] file not found.");
        }
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));

            byte[] bytes = new byte[1024];
            baos = new ByteArrayOutputStream();
            int n = 0;
            while((n = bis.read(bytes)) != -1) {
                baos.write(bytes, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bis != null) {
                bis.close();
                bis = null;
            }
        }
        return baos.toByteArray();
    }

    public static void base64StrToFile(String base64Str, String filePath) throws IOException {

        if(base64Str == null || base64Str.trim().length() == 0) {
            throw new IllegalArgumentException("base64Str can't be empty.");
        }

        File file = new File(filePath);
        if(!file.exists()) {
            boolean create = file.createNewFile();
            if(create) {
                System.out.println("[" + filePath + "]文件创建成功!");
            } else {
                System.out.println("[" + filePath + "]文件创建失败!");
            }
        } else {
            System.out.println("[" + filePath + "]文件已存在!");
        }

        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;

        bis = new BufferedInputStream(new BASE64DecoderStream(new ByteArrayInputStream(base64Str.getBytes())));
        byte[] data = new byte[4096];

        try {

            bos = new BufferedOutputStream(new FileOutputStream(file));
            int len = 0;
            while ((len = bis.read(data)) != -1) {
                bos.write(data, 0, len);
            }
            bos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                IOUtils.close(bis, bos);
            } catch (IOException ex3) {
                ex3.printStackTrace();
            }
        }

    }

}
