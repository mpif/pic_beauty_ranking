package com.mpif.beautyranking.util;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author: caishengzhi
 * @date: 2018-06-06 17:32
 */
public class ResponseContent {

    private HttpEntity entity;

    private InputStream content;

    public ResponseContent(HttpEntity entity) {
        this.entity = entity;
        try {
            this.content = entity.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponseContentString() {
        System.out.println("response:");

        StringBuilder sb = new StringBuilder();

        BufferedReader br = null;
        try {
            InputStreamReader isr = new InputStreamReader(content);
            br = new BufferedReader(isr);
            String line = "";
            while((line = br.readLine()) != null) {
//                System.out.println(line);
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // and ensure it is fully consumed
                EntityUtils.consume(entity);
                if(br != null) {
                    br.close();
                    br = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }



}
