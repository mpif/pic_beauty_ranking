package com.mpif.beautyranking.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * @author: mpif
 * @date: 2017-09-14 10:33
 * MD5工具类
 **/
public class Md5Utils {

    public static String getFileMd5Str(String filePath) {
        try {
            if(filePath == null || filePath.trim().length() == 0) {
                throw new IllegalArgumentException("filePath can not be empty.");
            }
            InputStream is = new FileInputStream(new File(filePath));
            return getMd5Str(is);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMd5Str(InputStream in) {
        try {
            byte[] strTemp = getBytes(in);
            return getMd5Str(strTemp);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMd5Str(String source) {
        try {
            byte[] strTemp = source.getBytes();
            return getMd5Str(strTemp);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMd5Str(byte[] sBytes) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {

            int j = sBytes.length;

            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = sBytes[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param inputStream
     * @return
     */
    public static byte[] getBytes(InputStream inputStream) {
        int buffSize = 8192;
        byte[] bytes = new byte[buffSize];
        int read = 0;
        byte[] md5Bytes = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            int totalBytes = 0;
            while ((read = inputStream.read(bytes, 0, buffSize)) > 0) {
                totalBytes = totalBytes + read;
                md5.update(bytes, 0, read);
            }
//            System.out.println("totalBytes:[" + totalBytes + "]");
            md5Bytes = md5.digest();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return md5Bytes;
    }

}
