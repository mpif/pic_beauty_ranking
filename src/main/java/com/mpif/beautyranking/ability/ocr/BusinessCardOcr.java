package com.mpif.beautyranking.ability.ocr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mpif.beautyranking.AbstractComputeVision;
import com.mpif.beautyranking.enums.ErrorCodeEnum;
import com.mpif.beautyranking.util.HttpClientV455;
import com.mpif.beautyranking.util.ResponseContent;
import com.mpif.beautyranking.util.StringsSortByDict;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: mpif
 * @Date: 2018-06-11 23:02
 */

public class BusinessCardOcr extends AbstractComputeVision {

    public BusinessCardOcr() {
        apiUrl = "https://api.ai.qq.com/fcgi-bin/ocr/ocr_bcocr";
        picPath = picRoot + "business card.jpg";
        try {
            image = getFileBase64Str(picPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        BusinessCardOcr businessCardOcr = new BusinessCardOcr();
        businessCardOcr.ocr();

    }

    public void ocr() {
        try {
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

            if(responseContent != null) {
                String jsonContent = responseContent.getResponseContentString();

                System.out.println("接口调用返回内容为:");
                System.out.println(jsonContent);

                this.parseItemList(jsonContent);

            } else {
                System.out.println("responseContent is null.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parseItemList(String jsonContent) throws IOException {
        JSONObject jsonObject = (JSONObject) JSON.parseObject(jsonContent);
        int ret = jsonObject.getInteger("ret");
        String msg = jsonObject.getString("msg");

        ErrorCodeEnum errorCodeEnum = ErrorCodeEnum.valueOf(ret);
        if(errorCodeEnum != null) {
            System.out.println("返回code=" + ret + ", " + errorCodeEnum.getDesc());
            System.out.println("返回msg：" + msg);

        }

        JSONObject dataObj = jsonObject.getJSONObject("data");

        if(dataObj != null) {

            JSONArray dataArray = dataObj.getJSONArray("item_list");

            if (dataArray != null) {
                for (int i = 0; i < dataArray.size(); i++) {
                    JSONObject itemObj = (JSONObject) dataArray.get(i);
                    System.out.println(itemObj.get("item"));
                    System.out.println(itemObj.get("itemstring"));
                }
            } else {
                System.out.println("data array is empty.");
            }

        }


    }

}
