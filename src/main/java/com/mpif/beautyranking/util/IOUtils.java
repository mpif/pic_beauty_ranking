package com.mpif.beautyranking.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * @author: mpif
 * @date: 2018-06-07 17:06
 */
public class IOUtils {

    public static void close(InputStream is, OutputStream os) throws IOException {
        close(is);
        close(os);
    }

    public static void close(InputStream is) throws IOException {
        if(is != null) {
            is.close();
            is = null;
        }
    }

    public static void close(OutputStream os) throws IOException {
        if (os != null) {
            os.flush();
            os.close();
            os = null;
        }
    }

    public static void close(Scanner sc) throws IOException {
        if(sc != null) {
            sc.close();
            sc = null;
        }
    }

}
