package com.mpif.beautyranking.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.*;

/**
 * @author: mpif
 * @date: 2018-06-06 09:28
 *
 * 可以配合com.codefans.practicetask.httpserver.HttpServerStartup.java一起使用，
 * 它是通过Spring-Boot启动一个http服务器
 *
 */
public class HttpClientV455 {

    public static void main(String[] args) {
        try {

            ResponseContent responseContent = null;

            String postUri = "http://localhost:8080/admin/addDomain";
            Map<String, String> params = new HashMap<String, String>();
            params.put("username", "zhangsan");
            params.put("password", "1234");
//            responseContent = doPost(postUri, params);

            String getUri = "http://localhost:8080/admin/queryGetString?username=zhangsan&password=123666888";
            responseContent = doGet(getUri);

            System.out.println("返回结果为:");
            System.out.println(responseContent.getResponseContentString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ResponseContent doPost(String uri, Map<String, Object> params) throws IOException {

        ResponseContent responseContent = null;

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setHeader("Connection", "Keep-Alive");

        int size = params.size();
        if(size > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Iterator<String> iter = params.keySet().iterator();
            String key = "";
            Object value = "";
            while (iter.hasNext()) {
                key = iter.next();
                value = params.get(key);
                nvps.add(new BasicNameValuePair(key, String.valueOf(value)));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        }

        CloseableHttpResponse response = httpclient.execute(httpPost);

//        try {
//            System.out.println(response.getStatusLine());
//            HttpEntity entity = response.getEntity();
//            // do something useful with the response body
//            responseContent = new ResponseContent(entity);
//        } finally {
//            response.close();
//        }
//        return responseContent;

        return wrapResponse(response);


    }

    public static ResponseContent doGet(String uri) throws IOException {

        ResponseContent responseContent = null;

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
//        HttpGet httpGet = new HttpGet("http://localhost:8080/admin/queryGetString?username=zhangsan&password=123666888");
        CloseableHttpResponse response = httpclient.execute(httpGet);
// The underlying HTTP connection is still held by the response object
// to allow the response content to be streamed directly from the network socket.
// In order to ensure correct deallocation of system resources
// the user MUST call CloseableHttpResponse#close() from a finally clause.
// Please note that if response content is not fully consumed the underlying
// connection cannot be safely re-used and will be shut down and discarded
// by the connection manager.
//        try {
//            System.out.println(response.getStatusLine());
//            HttpEntity entity = response.getEntity();
//            // do something useful with the response body
//
//            responseContent = new ResponseContent(entity);
//
//        } finally {
//            response.close();
//        }

        return wrapResponse(response);

    }

    public static ResponseContent wrapResponse(CloseableHttpResponse response) throws IOException {
        ResponseContent responseContent = null;
        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            // do something useful with the response body

            responseContent = new ResponseContent(entity);

        } finally {
//            response.close();
        }

        return responseContent;
    }

}
