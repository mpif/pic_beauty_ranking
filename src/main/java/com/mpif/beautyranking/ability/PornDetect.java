package com.mpif.beautyranking.ability;

import com.mpif.beautyranking.AbstractComputeVision;
import com.mpif.beautyranking.util.*;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: mpif
 * @Date: 2018-06-07 22:46
 */

public class PornDetect extends AbstractComputeVision {

    public PornDetect() {
        apiUrl = "https://api.ai.qq.com/fcgi-bin/vision/vision_porn";
    }

    public static void main(String[] args) {
        PornDetect pornDetect = new PornDetect();
        pornDetect.detectImgs();
    }

    public void detectImgs() {

        String[] imgPaths = new String[]{
//            "img01.jpg",
//            "img02.jpg",
//            "img03.jpg",
//            "img04.jpg",
//            "img05.jpg",
//            "pan-1008-1344.jpg"
            "porn-01.jpg",
            "porn-02.jpg",
            "porn-03.jpg",
            "porn-04.jpg",
            "porn-05.jpg"
        };

        for(int i = 0; i < imgPaths.length; i ++) {
            detect(picRoot + imgPaths[i]);
        }

    }


    public void detect(String imgPath) {
        try {

            image = getFileBase64Str(imgPath);

            //计算签名是map中不包括sign, 共5个参数, 且map中的value都是进过URL编码的
            Map<String, Object> paramsMap = getParamsMapForSign();
            String sortedDictStr = new StringsSortByDict().sortedDictStr(paramsMap);
            sortedDictStr = sortedDictStr + "&" + appKeyStr + "=" + appKey;
            System.out.println("sortedDictStr:");
            System.out.println(sortedDictStr);

            String signatureStr = DigestUtils.md5Hex(sortedDictStr).toUpperCase();
            System.out.println("md5Str:");
            System.out.println(signatureStr);

            //提交的时候map中包括sign, 共6个参数, 且map中的value都是没有进过URL编码的
            //之前出错就是因为提交了进过URL编码的image, 估计是验证签名时又对image的内容进行URL编码, 所以算出来的sign不匹配。
            Map<String, Object> postParamsMap = getPostParamsMap();
            postParamsMap.put(signKey, signatureStr);
            ResponseContent responseContent = HttpClientV455.doPost(apiUrl, postParamsMap);
            System.out.println("接口调用返回内容为:");
            System.out.println(JsonUtils.format(responseContent.getResponseContentString()));
//            System.out.println(responseContent.getResponseContentString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
